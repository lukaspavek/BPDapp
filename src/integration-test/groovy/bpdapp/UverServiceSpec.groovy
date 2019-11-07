package bpdapp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UverServiceSpec extends Specification {

    UverService uverService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Uver(...).save(flush: true, failOnError: true)
        //new Uver(...).save(flush: true, failOnError: true)
        //Uver uver = new Uver(...).save(flush: true, failOnError: true)
        //new Uver(...).save(flush: true, failOnError: true)
        //new Uver(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //uver.id
    }

    void "test get"() {
        setupData()

        expect:
        uverService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Uver> uverList = uverService.list(max: 2, offset: 2)

        then:
        uverList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        uverService.count() == 5
    }

    void "test delete"() {
        Long uverId = setupData()

        expect:
        uverService.count() == 5

        when:
        uverService.delete(uverId)
        sessionFactory.currentSession.flush()

        then:
        uverService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Uver uver = new Uver()
        uverService.save(uver)

        then:
        uver.id != null
    }
}
