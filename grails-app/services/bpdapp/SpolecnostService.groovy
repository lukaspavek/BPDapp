package bpdapp

import grails.gorm.services.Service

@Service(Spolecnost)
interface SpolecnostService {

    Spolecnost get(Serializable id)

    List<Spolecnost> list(Map args)

    Long count()

    void delete(Serializable id)

    Spolecnost save(Spolecnost spolecnost)

}