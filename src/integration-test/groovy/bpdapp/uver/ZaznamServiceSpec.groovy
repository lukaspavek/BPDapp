package bpdapp.uver

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ZaznamServiceSpec extends Specification {

    ZaznamServiceInterface zaznamService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Zaznam(...).save(flush: true, failOnError: true)
        //new Zaznam(...).save(flush: true, failOnError: true)
        //Zaznam zaznam = new Zaznam(...).save(flush: true, failOnError: true)
        //new Zaznam(...).save(flush: true, failOnError: true)
        //new Zaznam(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //zaznam.id
    }

    void "test get"() {
        setupData()

        expect:
        zaznamService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Zaznam> zaznamList = zaznamService.list(max: 2, offset: 2)

        then:
        zaznamList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        zaznamService.count() == 5
    }

    void "test delete"() {
        Long zaznamId = setupData()

        expect:
        zaznamService.count() == 5

        when:
        zaznamService.delete(zaznamId)
        sessionFactory.currentSession.flush()

        then:
        zaznamService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Zaznam zaznam = new Zaznam()
        zaznamService.save(zaznam)

        then:
        zaznam.id != null
    }
}
