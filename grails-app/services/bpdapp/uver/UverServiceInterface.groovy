package bpdapp.uver

import grails.gorm.services.Service

@Service(Uver)
interface UverServiceInterface {

    Uver get(Serializable id)

    List<Uver> list(Map args)

    Long count()

    void delete(Serializable id)

    Uver save(Uver uver)

}
