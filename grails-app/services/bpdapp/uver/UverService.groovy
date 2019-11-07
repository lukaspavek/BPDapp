package bpdapp.uver

import bpdapp.enumeration.TypZaznamu
import grails.gorm.transactions.Transactional

@Transactional
class UverService implements UverServiceInterface {

    def getDetails(Integer uverId) {
        /*
        TODO: zobrazení úvěrového rámce
        TODO: zobrazení součtu splátek
        TODO: zobrazení součtu čerpání
        TODO: zobrazení aktuální dlužné částky
        TODO: zobrazení dílčích výpočtů (jistina, úrok, splátka)
        TODO: upravit menu (Domů, Zpět na úvěr)
         */

        def details = [:]
        def uverItem = Uver.get(uverId)

        if (!uverItem) {
            println("UVER NENALEZEN!!!")
            return
        }

        def zaznamy = Zaznam.findAllByUver(uverItem).sort() {it.datum}

        details['maxUverovyRamec'] = uverItem.maxUverovyRamec
        details['listCerpani'] = zaznamy.findAll{it.typZaznamu == TypZaznamu.CERPANI}.sort {it.datum}
        details['listSplatky'] = zaznamy.findAll{it.typZaznamu == TypZaznamu.SPLATKA}.sort {it.datum}
        details['sumCerpani'] = details['listCerpani']*.castka.sum()
        details['sumSplatky'] = details['listSplatky']*.castka.sum()
        details['sumUroky'] = zaznamy*.urok.sum()
        //details['zustatek'] =

        return details
    }

    @Override
    Uver get(Serializable id) {
        return Uver.get(id)
    }

    @Override
    List<Uver> list(Map args) {
        return Uver.list(args)
    }

    @Override
    Long count() {
        return Uver.count()
    }

    @Override
    void delete(Serializable id) {
        Uver.deleteAll(id)
    }

    @Override
    Uver save(Uver uver) {
        return uver.save(flush: true)
    }
}
