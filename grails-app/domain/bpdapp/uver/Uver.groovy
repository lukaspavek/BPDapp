package bpdapp.uver

import bpdapp.Spolecnost

class Uver {

    String nazevUveru
    Date platnostOd
    BigDecimal maxUverovyRamec
    Integer delkaRoku
    BigDecimal urokovaSazba
    Double varSymb
    boolean mimoradnaSplatka
    boolean predcasneUkonceni
    String poznamka

    static hasMany = [zaznamy : Zaznam]
    static belongsTo = [spolecnost: Spolecnost]

    static constraints = {
        nazevUveru unique: true, blank: false
        varSymb unique: true, nullable: true, blank: true
        poznamka blank: true, nullable: true
    }
}
