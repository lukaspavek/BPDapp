package bpdapp.uver

import grails.gorm.services.Service

@Service(Urok)
interface UrokServiceInterface {

    Urok get(Serializable id)

    List<Urok> list(Map args)

    Long count()

    void delete(Serializable id)

    Urok save(Urok urok)

}