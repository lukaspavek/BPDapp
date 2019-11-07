package bpdapp.uver

import grails.gorm.services.Service

@Service(Zaznam)
interface ZaznamServiceInterface {

    Zaznam get(Serializable id)

    List<Zaznam> list(Map args)

    Long count()

    void delete(Serializable id)

    Zaznam save(Zaznam zaznam)

}