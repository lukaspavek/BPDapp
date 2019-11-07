package bpdapp.uver

import grails.validation.ValidationException

class UverController {

    UverService uverService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond uverService.list(params), model:[uverCount: uverService.count()]
    }

    def show(Long id) {
        respond uverService.get(id)
    }

    def create() {
        respond new Uver(params)
    }

    def save(Uver uver) {
        if (uver == null) {
            notFound()
            return
        }

        try {
            uverService.save(uver)
        } catch (ValidationException e) {
            respond uver.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'uver.label', default: 'Uver'), uver.id])
                redirect uver
            }
            '*' { respond uver, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond uverService.get(id)
    }

    def update(Uver uver) {
        if (uver == null) {
            notFound()
            return
        }

        try {
            uverService.save(uver)
        } catch (ValidationException e) {
            respond uver.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'uver.label', default: 'Uver'), uver.id])
                redirect uver
            }
            '*'{ respond uver, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        uverService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'uver.label', default: 'Uver'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def report(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (params.id == null) { params.id = 0 }

        def details = uverService.getDetails(params.id as int)

        if (!details) {
            notFound()
            return
        }

        respond (details: details)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'uver.label', default: 'Uver'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
