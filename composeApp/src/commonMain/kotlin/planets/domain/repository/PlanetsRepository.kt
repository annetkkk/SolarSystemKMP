package planets.domain.repository

import planets.domain.domain.Planet

interface PlanetsRepository {
    suspend fun getPlanets(): Result<List<Planet>>
    fun getPlanetById(planetId: Int): Planet?
}