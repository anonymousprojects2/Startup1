package com.example.startup1.repositories

import com.example.startup1.models.User
import com.example.startup1.utils.FirebaseClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserRepository {
    private val usersCollection = FirebaseClient.getUsersCollection()

    suspend fun createUser(user: User): Result<String> = withContext(Dispatchers.IO) {
        try {
            val docRef = usersCollection.add(user).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUser(sevarthId: String): Result<User?> = withContext(Dispatchers.IO) {
        try {
            val snapshot = usersCollection.whereEqualTo("sevarthId", sevarthId).get().await()
            val user = snapshot.documents.firstOrNull()?.toObject(User::class.java)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUser(user: User): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val snapshot = usersCollection.whereEqualTo("sevarthId", user.sevarthId).get().await()
            val document = snapshot.documents.firstOrNull() ?: throw Exception("User not found")
            usersCollection.document(document.id).set(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllUsers(): Result<List<User>> = withContext(Dispatchers.IO) {
        try {
            val snapshot = usersCollection.get().await()
            val users = snapshot.toObjects(User::class.java)
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteUser(sevarthId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val snapshot = usersCollection.whereEqualTo("sevarthId", sevarthId).get().await()
            val document = snapshot.documents.firstOrNull() ?: throw Exception("User not found")
            usersCollection.document(document.id).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 