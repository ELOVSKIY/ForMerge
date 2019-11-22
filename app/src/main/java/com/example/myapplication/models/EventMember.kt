package com.example.myapplication.models

class EventMember(
        val name: String,
        val phoneNumb: String,
        val contactPhoto: String?
) {
    private var _invited = false
    val invited: Boolean
        get() = _invited

    fun changeState() {
        _invited = !_invited
    }

    override fun equals(other: Any?): Boolean {
        return if (other == null){
            false
        }else{
            val member = other as EventMember
            this.name == member.name
        }
    }

    override fun hashCode(): Int {
        return phoneNumb.hashCode()
    }
}