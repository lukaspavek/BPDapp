package bpdapp

import bpdapp.uver.Uver

class Spolecnost {

    String nazev

    static hasMany = [uvery: Uver]

    static constraints = {
        nazev unique: true, blank: false, nullable: false
    }
}
