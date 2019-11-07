package bpdapp.uver

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UrokController {

    UrokServiceInterface urokService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond urokService.list(params), model:[urokCount: urokService.count()]
    }

    def show(Long id) {
        respond urokService.get(id)
    }

    def create() {
        respond new Urok(params)
    }

    def save(Urok urok) {
        if (urok == null) {
            notFound()
            return
        }

        try {
            urokService.save(urok)
        } catch (ValidationException e) {
            respond urok.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'urok.label', default: 'Urok'), urok.id])
                redirect urok
            }
            '*' { respond urok, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond urokService.get(id)
    }

    def update(Urok urok) {
        if (urok == null) {
            notFound()
            return
        }

        try {
            urokService.save(urok)
        } catch (ValidationException e) {
            respond urok.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'urok.label', default: 'Urok'), urok.id])
                redirect urok
            }
            '*'{ respond urok, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        urokService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'urok.label', default: 'Urok'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'urok.label', default: 'Urok'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
