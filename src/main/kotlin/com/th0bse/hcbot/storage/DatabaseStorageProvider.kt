package com.th0bse.hcbot.storage

import jetbrains.exodus.entitystore.Entity
import jetbrains.exodus.entitystore.PersistentEntityStore
import jetbrains.exodus.entitystore.PersistentEntityStores

object DatabaseStorageProvider: StorageProvider {
    private val entityStore: PersistentEntityStore = PersistentEntityStores.newInstance("./db")

    /**
     * This is bad, replace this with some way of exposing a readonly transaction to be executed.
     * That way we make sure nobody is committing junk into the database.
     */
    fun getEntityStore(): PersistentEntityStore {
        return entityStore
    }

    fun clearDatabase() {
        entityStore.clear()
    }

    override fun addUser(user: User) {
        if (getUserByTag(user.tag) != null) return
        entityStore.executeInTransaction {
            it.newEntity("user").apply {
                setProperty("discord-id", user.id)
                setProperty("name", user.name)
                setProperty("tag", user.tag)
            }
        }
    }

    override fun getUserByTag(tag: String): User? {
        var u: User? = null
        entityStore.executeInReadonlyTransaction {
            val entity = it.find("user", "tag", tag)
            // create User object to return
            if (entity.first != null) {
                val n = entity.first?.getProperty("name") as String
                val t = entity.first?.getProperty("tag") as String
                val i = entity.first?.getProperty("discord-id") as String
                u = User(n, t, i)
            }
        }
        return u
    }

    override fun getUserById(id: String): User? {
        var u: User? = null
        entityStore.executeInReadonlyTransaction {
            val entity = it.find("user", "id", id)
            // create User object to return
            if (entity.first != null) {
                val n = entity.first?.getProperty("name") as String
                val t = entity.first?.getProperty("tag") as String
                val i = entity.first?.getProperty("id") as String
                u = User(n, t, i)
            }
        }
        return u
    }

    override fun addMessage(message: Message) {
        addUser(message.author)
        var userEntity: Entity
        entityStore.executeInTransaction {
            it.newEntity("message").apply {
                setProperty("timestamp", message.timestamp.toEpochMilli())
                setBlobString("content", message.content)
                userEntity = it.find("user", "tag", message.author.tag).first()
                addLink("author", userEntity)
                userEntity.addLink("message", this)
            }
        }
    }
}