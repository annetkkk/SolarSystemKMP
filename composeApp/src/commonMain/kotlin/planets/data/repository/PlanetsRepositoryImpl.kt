package planets.data.repository

import planets.data.service.PlanetsService
import planets.domain.domain.Planet
import planets.domain.repository.PlanetsRepository
import planets.domain.repository.Result

class PlanetsRepositoryImpl(
    private val service: PlanetsService,
) : PlanetsRepository {

    override suspend fun getPlanets(): Result<List<Planet>> {
        return try {
            Result.Success(service.getPlanets())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun getPlanetById(planetId: Int): Planet? {
       return service.getPlanetById(planetId)
    }

}