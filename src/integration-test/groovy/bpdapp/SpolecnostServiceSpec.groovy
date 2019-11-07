package bpdapp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SpolecnostServiceSpec extends Specification {

    SpolecnostService spolecnostService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Spolecnost(...).save(flush: true, failOnError: true)
        //new Spolecnost(...).save(flush: true, failOnError: true)
        //Spolecnost spolecnost = new Spolecnost(...).save(flush: true, failOnError: true)
        //new Spolecnost(...).save(flush: true, failOnError: true)
        //new Spolecnost(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //spolecnost.id
    }

    void "test get"() {
        setupData()

        expect:
        spolecnostService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Spolecnost> spolecnostList = spolecnostService.list(max: 2, offset: 2)

        then:
        spolecnostList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        spolecnostService.count() == 5
    }

    void "test delete"() {
        Long spolecnostId = setupData()

        expect:
        spolecnostService.count() == 5

        when:
        spolecnostService.delete(spolecnostId)
        sessionFactory.currentSession.flush()

        then:
        spolecnostService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Spolecnost spolecnost = new Spolecnost()
        spolecnostService.save(spolecnost)

        then:
        spolecnost.id != null
    }
}
