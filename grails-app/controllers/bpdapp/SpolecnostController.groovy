package bpdapp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SpolecnostController {

    SpolecnostService spolecnostService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond spolecnostService.list(params), model:[spolecnostCount: spolecnostService.count()]
    }

    def show(Long id) {
        respond spolecnostService.get(id)
    }

    def create() {
        respond new Spolecnost(params)
    }

    def save(Spolecnost spolecnost) {
        if (spolecnost == null) {
            notFound()
            return
        }

        try {
            spolecnostService.save(spolecnost)
        } catch (ValidationException e) {
            respond spolecnost.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'spolecnost.label', default: 'Spolecnost'), spolecnost.id])
                redirect spolecnost
            }
            '*' { respond spolecnost, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond spolecnostService.get(id)
    }

    def update(Spolecnost spolecnost) {
        if (spolecnost == null) {
            notFound()
            return
        }

        try {
            spolecnostService.save(spolecnost)
        } catch (ValidationException e) {
            respond spolecnost.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'spolecnost.label', default: 'Spolecnost'), spolecnost.id])
                redirect spolecnost
            }
            '*'{ respond spolecnost, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        spolecnostService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'spolecnost.label', default: 'Spolecnost'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'spolecnost.label', default: 'Spolecnost'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
