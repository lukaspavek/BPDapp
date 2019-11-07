package bpdapp.uver

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UrokServiceSpec extends Specification {

    UrokServiceInterface urokService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Urok(...).save(flush: true, failOnError: true)
        //new Urok(...).save(flush: true, failOnError: true)
        //Urok urok = new Urok(...).save(flush: true, failOnError: true)
        //new Urok(...).save(flush: true, failOnError: true)
        //new Urok(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //urok.id
    }

    void "test get"() {
        setupData()

        expect:
        urokService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Urok> urokList = urokService.list(max: 2, offset: 2)

        then:
        urokList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        urokService.count() == 5
    }

    void "test delete"() {
        Long urokId = setupData()

        expect:
        urokService.count() == 5

        when:
        urokService.delete(urokId)
        sessionFactory.currentSession.flush()

        then:
        urokService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Urok urok = new Urok()
        urokService.save(urok)

        then:
        urok.id != null
    }
}
