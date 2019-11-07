package bpdapp.uver

import bpdapp.Spolecnost

class Uver {

    String nazevUveru
    Date platnostOd
    Date platnostDo
    BigDecimal maxUverovyRamec
    Integer delkaRoku
    //BigDecimal urokovaSazba
    Double varSymb
    boolean mimoradnaSplatka
    boolean predcasneUkonceni
    boolean interni
    Integer denSplatky
    String poznamka

    static hasMany = [uroky : Urok, zaznamy : Zaznam]
    static belongsTo = [spolecnost: Spolecnost]

    static constraints = {
        nazevUveru unique: true, blank: false
        varSymb unique: true, nullable: true, blank: true
        poznamka blank: true, nullable: true
        denSplatky range: 1..31, blank: false, nullable: false
        poznamka widget: 'textarea'
    }
}
