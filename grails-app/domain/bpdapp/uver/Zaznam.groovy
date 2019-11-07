package bpdapp.uver

import bpdapp.enumeration.TypZaznamu

class Zaznam {

    Date datum
    BigDecimal castka
    TypZaznamu typZaznamu
    BigDecimal urokovaSazba      // neni-li uvedeno tak doplnit z definice Uveru
    Double varSymb
    BigDecimal urok
    BigDecimal zustatek

    // dopocitat UROK, JISTINA

    static belongsTo = [uver : Uver]

    static constraints = {
        varSymb nullable: true, blank: true
        //castka nullable: true, blank: true
        urokovaSazba nullable: true, blank: false
        urok nullable: true, blank: true
        zustatek nullable: true, blank: true
    }

    def onLoad() {
        //log.debug "Loading ${id}"
        println("Loading ZAZNAM ${id}")
        //if (!urokovaSazba) urokovaSazba = Uver.get(uver).urokovaSazba
    }

    def beforeInsert() {
        println("beforeInsert .....")
        //println("BEFORE-INSERT zaznam = " + Uver.get(uver).urokovaSazba)
        //if (!urokovaSazba) urokovaSazba = Uver.get(uver).urokovaSazba
    }

    def beforeUpdate() {
        println("beforeUpdate .....")
        //if (!urokovaSazba) urokovaSazba = Uver.get(uver).urokovaSazba
    }

    // VALIDACE VSTUPU
    // - kdyz PREDPIS - nepovinne "castka", "varSymb"
    // - kdyz není "procento" - doplnit z Uveru
}
