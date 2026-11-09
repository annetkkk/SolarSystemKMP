package planets.data.repository

import kotlinx.coroutines.test.runTest
import planets.domain.domain.Planet
import planets.domain.repository.PlanetsRepository
import kotlin.test.Test
import kotlin.test.assertTrue
import planets.domain.repository.Result

class PlanetsRepositoryImplTest {

    @Test
    fun testWeatherRepositoryInterface() = runTest {
        // Given - Create a fake implementation for testing
        val fakeRepository = FakePlanetsRepository()

        // When
        val planetsResult = fakeRepository.getPlanets()

        // Then
        assertTrue(planetsResult.isSuccess, "The call should be successful")
        val weatherList = planetsResult.getOrNull()
        assertTrue(weatherList?.isNotEmpty() == true, "list should not be empty")
    }

    @Test
    fun testWeatherRepositoryErrorHandling() = runTest {
        // Given - Repository configured to return error
        val fakeRepository = FakePlanetsRepository(shouldReturnError = true)

        // When
        val result = fakeRepository.getPlanets()

        // Then
        assertTrue(result.isError, "Should return failure when configured for error")
    }

    @Test
    fun testWeatherDomainModelFields() = runTest {
        // Given
        val fakeRepository = FakePlanetsRepository()

        // When
        val result = fakeRepository.getPlanets()
        val planetsList = result.getOrNull()
        val planet = planetsList?.first()
        // Then - Verify all required fields are present
        planet?.let {
            assertTrue(it.name.isNotEmpty(), "Name should not be empty")
            assertTrue(it.description.isNotEmpty(), "Description should not be empty")
            assertTrue(it.mass.contains("kg"), "Mass should be in kg")
            assertTrue(it.planetOrder in 1..8, "Order should be correct")
        } ?: run {
            kotlin.test.fail("Planet should not be null")
        }
    }
}

/**
 * Fake PlanetsRepository implementation for testing
 */
class FakePlanetsRepository(
    private val shouldReturnError: Boolean = false
) : PlanetsRepository {

    private val samplePlanet = Planet(
        id = 1,
        planetId = 1,
        name = "Earth",
        description = "Test planet description",
        img = "",
        imgDescription = "",
        mass = "5.97×10²⁴ kg",
        volume = "1.08×10¹² km³",
        planetOrder = 3,
        source = "Wikipedia",
        wikiLink = "",
    )
    private val samplePlanetsList = listOf(
        samplePlanet
    )


    override suspend fun getPlanets(): Result<List<Planet>> {
        return if (shouldReturnError) {
            Result.Error(Exception("Test error"))
        } else {
            Result.Success(samplePlanetsList)
        }
    }


    override fun getPlanetById(planetId: Int): Planet? {
        return samplePlanet
    }

}