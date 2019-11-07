package bpdapp

import bpdapp.enumeration.TypZaznamu
import bpdapp.uver.Uver
import bpdapp.uver.Zaznam

class BootStrap {

    def init = { servletContext ->

        // spolecnost
        Spolecnost spol1 = new Spolecnost(nazev: "Novák a spol.").save()
        Spolecnost spol2 = new Spolecnost(nazev: "Chocholoušek s.r.o.").save()
        Spolecnost spol3 = new Spolecnost(nazev: "Ječmínek a.s.").save()

        // uvery
        Uver u1 = new Uver(spolecnost:[id:1], nazevUveru: "Úvěr 1", platnostOd: new Date(),   maxUverovyRamec: 100, poznamka: "Pozn1", predcasneUkonceni: true, delkaRoku: 365, urokovaSazba: 1.25).save()
        Uver u2 = new Uver(spolecnost:[id:1], nazevUveru: "Úvěr 2", platnostOd: new Date()+1, maxUverovyRamec: 200, poznamka: "Pozn2", predcasneUkonceni: true, delkaRoku: 360, urokovaSazba: 2.40).save()
        Uver u3 = new Uver(spolecnost:[id:1], nazevUveru: "Úvěr 3", platnostOd: new Date()+2, maxUverovyRamec: 300, poznamka: "Pozn3", predcasneUkonceni: true, delkaRoku: 365, urokovaSazba: 3.05).save()
        Uver u4 = new Uver(spolecnost:[id:2], nazevUveru: "Úvěr 4", platnostOd: new Date()+3, maxUverovyRamec: 400, poznamka: "Pozn4", predcasneUkonceni: true, delkaRoku: 360, urokovaSazba: 4.92).save()
        Uver u5 = new Uver(spolecnost:[id:2], nazevUveru: "Úvěr 5", platnostOd: new Date()+4, maxUverovyRamec: 500, poznamka: "Pozn5", predcasneUkonceni: true, delkaRoku: 365, urokovaSazba: 5.66).save()

        // zaznamy
/*
        Zaznam z1 = new Zaznam(typZaznamu: TypZaznamu.CERPANI, castka: 369.5, datum: new Date(), uver: [id:1], urokovaSazba: 1.5, varSymb: 111).save()
        Zaznam z2 = new Zaznam(typZaznamu: TypZaznamu.PREDPIS, castka: 1.5, datum: new Date()+10, uver: [id:3], urokovaSazba: 2, varSymb: 9876543210).save()
        Zaznam z3 = new Zaznam(typZaznamu: TypZaznamu.SPLATKA, castka: 20.5, datum: new Date()+20, uver: [id:3], urokovaSazba: 3.3, varSymb: 333).save()
        Zaznam z4 = new Zaznam(typZaznamu: TypZaznamu.CERPANI, castka: 20.05, datum: new Date()+25, uver: [id:3], urokovaSazba: 4.56, varSymb: 222).save()

        Random rnd = new Random()
        Integer i = 0
        for (i=1; i<6; i++) { new Zaznam(typZaznamu: TypZaznamu.SPLATKA, castka: i*10, datum: new Date()+(i*rnd.nextInt(30)), uver: [id:1], urokovaSazba: i+rnd.nextFloat(), varSymb: i*100*rnd.nextFloat()).save() }
        for (i=1; i<6; i++) { new Zaznam(typZaznamu: TypZaznamu.SPLATKA, castka: i*10, datum: new Date()+(i*rnd.nextInt(30)), uver: [id:3], urokovaSazba: i+rnd.nextFloat(), varSymb: i*100*rnd.nextFloat()).save() }
*/

    }
    def destroy = {
    }
}
