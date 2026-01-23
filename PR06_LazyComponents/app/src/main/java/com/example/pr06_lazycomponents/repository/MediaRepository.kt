package com.example.pr06_lazycomponents.repository

import com.example.pr06_lazycomponents.model.Media
import com.example.pr06_lazycomponents.model.MediaDetails
import com.example.pr06_lazycomponents.model.MediaType
import com.example.pr06_lazycomponents.R

class MediaRepository {
    fun getMediaList(): MutableList<Media> {
        val mediaList: MutableList<Media> = mutableListOf()

        // Película 1: Inception
        mediaList.add(Media(
            title = "Inception",
            mediaType = MediaType.MOVIE,
            genre = "Ciencia Ficción",
            image = R.drawable.bulbasaur, // Temporal - cambiar por imagen real
            year = 2010,
            rating = 8.8,
            description = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños recibe la tarea inversa de plantar una idea en la mente de un CEO.",
            details = MediaDetails(
                duration = "2h 28min",
                director = "Christopher Nolan",
                cast = listOf("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page")
            )
        ))

        // Serie 1: Breaking Bad
        mediaList.add(Media(
            title = "Breaking Bad",
            mediaType = MediaType.SERIES,
            genre = "Drama",
            image = R.drawable.charmander, // Temporal - cambiar por imagen real
            year = 2008,
            rating = 9.5,
            description = "Un profesor de química diagnosticado con cáncer se asocia con un exalumno para fabricar y vender metanfetamina para asegurar el futuro de su familia.",
            details = MediaDetails(
                duration = "47min/episodio",
                director = "Vince Gilligan",
                cast = listOf("Bryan Cranston", "Aaron Paul", "Anna Gunn"),
                seasons = 5,
                episodes = 62
            )
        ))

        // Película 2: The Dark Knight
        mediaList.add(Media(
            title = "The Dark Knight",
            mediaType = MediaType.MOVIE,
            genre = "Acción",
            image = R.drawable.squirtle, // Temporal - cambiar por imagen real
            year = 2008,
            rating = 9.0,
            description = "Batman debe aceptar una de las pruebas psicológicas y físicas más grandes para luchar contra la injusticia, encarnada por el Joker.",
            details = MediaDetails(
                duration = "2h 32min",
                director = "Christopher Nolan",
                cast = listOf("Christian Bale", "Heath Ledger", "Aaron Eckhart")
            )
        ))

        // Serie 2: Stranger Things
        mediaList.add(Media(
            title = "Stranger Things",
            mediaType = MediaType.SERIES,
            genre = "Ciencia Ficción",
            image = R.drawable.pikachu, // Temporal - cambiar por imagen real
            year = 2016,
            rating = 8.7,
            description = "Cuando un niño desaparece, su madre, un jefe de policía y sus amigos deben enfrentar fuerzas terroríficas para recuperarlo.",
            details = MediaDetails(
                duration = "51min/episodio",
                director = "The Duffer Brothers",
                cast = listOf("Millie Bobby Brown", "Finn Wolfhard", "Winona Ryder"),
                seasons = 4,
                episodes = 34
            )
        ))

        // Película 3: Pulp Fiction
        mediaList.add(Media(
            title = "Pulp Fiction",
            mediaType = MediaType.MOVIE,
            genre = "Crimen",
            image = R.drawable.jigglypuff, // Temporal - cambiar por imagen real
            year = 1994,
            rating = 8.9,
            description = "Las vidas de dos sicarios, un boxeador, la esposa de un gánster y dos bandidos se entrelazan en cuatro historias de violencia y redención.",
            details = MediaDetails(
                duration = "2h 34min",
                director = "Quentin Tarantino",
                cast = listOf("John Travolta", "Uma Thurman", "Samuel L. Jackson")
            )
        ))

        // Serie 3: The Crown
        mediaList.add(Media(
            title = "The Crown",
            mediaType = MediaType.SERIES,
            genre = "Drama",
            image = R.drawable.meowth, // Temporal - cambiar por imagen real
            year = 2016,
            rating = 8.6,
            description = "Sigue la vida de la Reina Isabel II desde la década de 1940 hasta los tiempos modernos.",
            details = MediaDetails(
                duration = "58min/episodio",
                director = "Peter Morgan",
                cast = listOf("Claire Foy", "Olivia Colman", "Imelda Staunton"),
                seasons = 6,
                episodes = 60
            )
        ))

        // Película 4: Interstellar
        mediaList.add(Media(
            title = "Interstellar",
            mediaType = MediaType.MOVIE,
            genre = "Ciencia Ficción",
            image = R.drawable.psyduck, // Temporal - cambiar por imagen real
            year = 2014,
            rating = 8.6,
            description = "Un equipo de exploradores viaja a través de un agujero de gusano en el espacio en un intento de asegurar la supervivencia de la humanidad.",
            details = MediaDetails(
                duration = "2h 49min",
                director = "Christopher Nolan",
                cast = listOf("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain")
            )
        ))

        // Serie 4: Game of Thrones
        mediaList.add(Media(
            title = "Game of Thrones",
            mediaType = MediaType.SERIES,
            genre = "Fantasía",
            image = R.drawable.golbat, // Temporal - cambiar por imagen real
            year = 2011,
            rating = 9.2,
            description = "Nueve familias nobles luchan por el control de las tierras de Westeros, mientras un antiguo enemigo regresa después de estar dormido durante milenios.",
            details = MediaDetails(
                duration = "57min/episodio",
                director = "David Benioff, D.B. Weiss",
                cast = listOf("Emilia Clarke", "Kit Harington", "Peter Dinklage"),
                seasons = 8,
                episodes = 73
            )
        ))

        // Película 5: The Shawshank Redemption
        mediaList.add(Media(
            title = "The Shawshank Redemption",
            mediaType = MediaType.MOVIE,
            genre = "Drama",
            image = R.drawable.rattata, // Temporal - cambiar por imagen real
            year = 1994,
            rating = 9.3,
            description = "Dos hombres encarcelados se unen durante varios años, encontrando consuelo y eventual redención a través de actos de decencia común.",
            details = MediaDetails(
                duration = "2h 22min",
                director = "Frank Darabont",
                cast = listOf("Tim Robbins", "Morgan Freeman", "Bob Gunton")
            )
        ))

        // Serie 5: The Mandalorian
        mediaList.add(Media(
            title = "The Mandalorian",
            mediaType = MediaType.SERIES,
            genre = "Ciencia Ficción",
            image = R.drawable.spearow, // Temporal - cambiar por imagen real
            year = 2019,
            rating = 8.7,
            description = "Las aventuras de un cazarrecompensas solitario en los confines de la galaxia, lejos de la autoridad de la Nueva República.",
            details = MediaDetails(
                duration = "40min/episodio",
                director = "Jon Favreau",
                cast = listOf("Pedro Pascal", "Gina Carano", "Carl Weathers"),
                seasons = 3,
                episodes = 24
            )
        ))

        return mediaList
    }
}
