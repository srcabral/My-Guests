package br.com.srcabral.myguests.service.constants

class GuestConstants private constructor(){
    companion object {
        const val GUESTID = "guestId"
    }

    object FILTER {
        const val ABSENT = 0
        const val PRESENT = 1
        const val DEFAULT = 2
    }
}