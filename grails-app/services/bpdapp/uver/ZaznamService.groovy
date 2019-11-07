package bpdapp.uver

import bpdapp.enumeration.TypZaznamu
import grails.gorm.services.Service
import grails.validation.ValidationException
import bpdapp.exceptions.IsNotCerpaniException

@Service(Zaznam)
class ZaznamService implements ZaznamServiceInterface {

    @Override
    Zaznam get(Serializable id) {
        return Zaznam.get(id)
    }

    @Override
    List<Zaznam> list(Map args) {
        return Zaznam.list(args)
    }

    @Override
    Long count() {
        return Zaznam.count()
    }

    @Override
    void delete(Serializable id) {
        Zaznam.deleteAll(id)
    }

    @Override
    Zaznam save(Zaznam zaznam) {

        if (kontrolaZaznamu(zaznam)) {
            this.getZustatek(zaznam)
            this.getUrok(zaznam)
            // TODO: doplnit nevyplneny var. symbol

            return zaznam.save(flush: true)
        } else {
            throw new IsNotCerpaniException("Prvni zaznam musi byt CERPANI")
        }

    }

    boolean kontrolaZaznamu(Zaznam zaznam) {
        /*
        TODO: kontrola prvniho zaznamu pred platnosti uveru
        TODO: overi maximalni castku pro cerpani
         */
        if (!zaznam) { return false }

        // prvni zaznam musi byt cerpani
        if (isPrvniZaznam(zaznam) && zaznam.typZaznamu != TypZaznamu.CERPANI) {
            println("Kontrola zaznamu je NOK")
            return false
        }

        println("Kontrola zaznamu je OK")
        return true
    }

    boolean isPrvniZaznam(Zaznam zaznam) {
        def zaznamy = Zaznam.findAllByUver(zaznam.uver).sort() {it.datum}

        if (zaznamy.size() == 0) {
            println("OK: Prvni zaznam pro uver " + zaznam.uver)
            return true
        }

        if (zaznamy.first().datum > zaznam.datum) {
            println("OK: Vkladany zaznam je starsiho data.")
            return true
        }

        println("NOK: Zaznam pro uver '" + zaznam.uver + "' neni prvni v seznamu")
        return false
    }

    Zaznam findPrevZaznam(Zaznam zaznam) {
        Zaznam prevZaznam = null
        def posledni

        def seznam = Zaznam.findAllByUver(Uver.get(zaznam.uverId)).sort() {it.datum}
        def var = seznam.findAll {it.datum < (zaznam.datum)}

        if (!var.isEmpty()) posledni = var.last()

        return posledni
        //return Zaznam.findAllByUver(zaznam.uverId).sort{it.datum}.last()
    }

    Integer getPocetDnu(Zaznam oldZ, Zaznam newZ) {
        println("old = " + oldZ.properties)
        println("new = " + newZ.properties)
        return newZ.datum - oldZ.datum
    }

    BigDecimal getUrok(Zaznam zaznam) {
        def prevZaznam = findPrevZaznam(zaznam)
        def zustatek = Uver.find(zaznam.uver).maxUverovyRamec
        def urokovaSazba = zaznam.urokovaSazba
        def delkaRoku = Uver.find(zaznam.uver).delkaRoku
        def pocetDnu

        println("PREVZAZNAM = " + prevZaznam)

        if (prevZaznam) { zustatek = prevZaznam.zustatek }

        if (zaznam.urokovaSazba == null){
            urokovaSazba = zaznam.urokovaSazba = Uver.find(zaznam.uver).urokovaSazba   // sazba z definice uveru
        }

        if (!prevZaznam) { pocetDnu = 0}
        else {
            println("POCET DNU OD PREDCHOZIHO ZAZNAMU")
            pocetDnu = getPocetDnu(prevZaznam,zaznam)
        }

        def vypocet = zustatek * (urokovaSazba/100) / delkaRoku * pocetDnu

        zaznam.urok = vypocet

        println("********** getUrok *****************")
        println("zustatek = " + zustatek)
        println("urokovaSazba = " + urokovaSazba)
        println("delkaRoku = " + delkaRoku)
        println("pocetDnu = " + pocetDnu)
        println("VYPOCET = " + vypocet)
        println("getUrok output = " + zaznam.urok)
        println("************************************")
        return zaznam.urok
    }

    Zaznam getZustatek(Zaznam zaznam) {
        if (isPrvniZaznam(zaznam) || findPrevZaznam(zaznam)==null) {
            // zustatek pro prvni zaznam = castka cerpani
            println("zustatek pro prvni zaznam = " + zaznam.castka)
            zaznam.zustatek = zaznam.castka
        } else {
            Integer korekce
            if (zaznam.typZaznamu == TypZaznamu.SPLATKA) {
                korekce = (1)
            } else if (zaznam.typZaznamu == TypZaznamu.CERPANI) {
                korekce = (-1)
            }

            def prevZ = findPrevZaznam(zaznam).zustatek.toBigDecimal()
            def urok = getUrok(zaznam).toBigDecimal()
            def zaz = zaznam.castka.toBigDecimal()

            println("prevZ = " + prevZ.toString())
            println("urok = " + urok.toString())
            println("zaz = " + zaz.toString())

            def zzzz = findPrevZaznam(zaznam).zustatek.toBigDecimal() + (getUrok(zaznam).toBigDecimal() ?: '0' )- (zaznam.castka.toBigDecimal() * korekce)
            println("zzzz = " + zzzz)

            zaznam.zustatek = zzzz

        }
        println("getZustatek = " + zaznam.zustatek)
        return zaznam
    }

    def recalkulate(Uver uver) {
        /*
        TODO: prepocitat vsechny zaznamy pro uver
         */
    }

    def handleIsNotCerpaniException(Exception e) {
        //Move this translation to src/groovy/utility if feasible
        flash.error = g.message(code:'user.input.error', args:'[${e.value}]')
    }
}
