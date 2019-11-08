package bpdapp.uver

import grails.gorm.transactions.Transactional
import bpdapp.uver.Zaznam

@Transactional
class UrokService implements UrokServiceInterface {

    @Override
    Urok get(Serializable id) {
        return Urok.get(id)
    }

    @Override
    List<Urok> list(Map args) {
        return Urok.list(args)
    }

    @Override
    Long count() {
        return Urok.count()
    }

    @Override
    void delete(Serializable id) {
        Urok.deleteAll(Urok.get(id))
    }

    @Override
    Urok save(Urok urok) {
        return urok.save(flush: true)
    }

    Urok findAktualniUrok(Zaznam zaznam) {
        def uroky = Urok.findAllByUver(zaznam.uver).sort(){it.platnostOd}
        def urok = uroky.findAll(){ it.platnostOd < zaznam.datum }.max(){it.platnostOd}

        if (!urok) urok = null

        println("findAktualniUrok = " + urok)
        return urok
    }
}
