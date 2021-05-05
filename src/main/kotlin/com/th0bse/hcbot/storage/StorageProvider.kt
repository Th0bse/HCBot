package com.th0bse.hcbot.storage

// TODO: add documentation
interface StorageProvider {

    fun addUser(user: User)

    fun getUserByTag(tag: String): User?

    fun getUserById(id: String):  User?

    fun addMessage(message: Message)
}