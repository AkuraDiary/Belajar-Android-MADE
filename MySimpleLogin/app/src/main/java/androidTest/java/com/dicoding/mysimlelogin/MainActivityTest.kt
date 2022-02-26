package androidTest.java.com.dicoding.mysimlelogin

import com.dicoding.core.UserRepository
import com.dicoding.mysimplelogin.storageModule
import org.junit.After
import org.koin.core.context.loadKoinModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class MainActivityTest : KoinTest{

    private val userRepository : UserRepository by inject()
    private val username = "dicoding"

    @Before
    fun before(){
        loadKoinModules(storageModule)
        userRepository.loginUser(username)
    }

    @After
    fun after(){
        stopKoin()
    }

    @Test
    fun getUsernameFromRepository(){
        val requestedUsername = userRepository.getUser()
        assertEquals(username, requestedUsername)
    }

}