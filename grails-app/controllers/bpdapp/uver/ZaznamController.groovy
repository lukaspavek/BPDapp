package bpdapp.uver

import bpdapp.exceptions.IsNotCerpaniException
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class ZaznamController {

    ZaznamService zaznamService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond zaznamService.list(params), model:[zaznamCount: zaznamService.count()]
    }

    def show(Long id) {
        respond zaznamService.get(id)
    }

    def create() {
        respond new Zaznam(params)
    }

    def save(Zaznam zaznam) {
        if (zaznam == null) {
            notFound()
            return
        }

        println("VSTUP: " + zaznam.getProperties().toString() )

        try {
            zaznamService.save(zaznam)
        } catch (IsNotCerpaniException excIsNot) {
            //flash.message = message(code: 'default.not.first', args: [message(code: 'zaznam.label', default 'Zaznam'), zaznam.id])
            flash.message = message(code: 'default.not.first')
            respond zaznam.errors, view:'create'
            println("EXCEPTION: " + excIsNot)
            return
        }  catch (ValidationException ve) {
            respond zaznam.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'zaznam.label', default: 'Zaznam'), zaznam.id])
                println("ZAZNAM = " + zaznam)
                redirect zaznam
            }
            '*' { respond zaznam, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond zaznamService.get(id)
    }

    def update(Zaznam zaznam) {
        if (zaznam == null) {
            notFound()
            return
        }

        try {
            zaznamService.save(zaznam)
        } catch (ValidationException e) {
            respond zaznam.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'zaznam.label', default: 'Zaznam'), zaznam.id])
                redirect zaznam
            }
            '*'{ respond zaznam, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        zaznamService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'zaznam.label', default: 'Zaznam'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'zaznam.label', default: 'Zaznam'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
