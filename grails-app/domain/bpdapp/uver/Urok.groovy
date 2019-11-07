package bpdapp.uver

class Urok {

    Date platnostOd
    BigDecimal urokovaSazba

    static belongsTo = [uver : Uver]

    static constraints = {
    }
}
