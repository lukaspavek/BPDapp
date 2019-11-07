package bpdapp.uver

import bpdapp.Spolecnost

class Uver {

    String nazevUveru
    Date platnostOd
    BigDecimal maxUverovyRamec
    Integer delkaRoku
    //BigDecimal urokovaSazba
    Double varSymb
    boolean mimoradnaSplatka
    boolean predcasneUkonceni
    boolean interni
    String poznamka

    static hasMany = [uroky : Urok, zaznamy : Zaznam]
    static belongsTo = [spolecnost: Spolecnost]

    static constraints = {
        nazevUveru unique: true, blank: false
        varSymb unique: true, nullable: true, blank: true
        poznamka blank: true, nullable: true
    }
}
