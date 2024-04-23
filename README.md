# ZekonyPokemons
Small app that uses PokeApi to get information about all pokemons there are.
I created this app to learn using Paging.

## Technologies used in this project

- Language: Kotlin
- UI: Jetpack Compose
- Orbit Mvi
- Coroutines
- Retrofit
- Koin
- Room
- Androidx Paging

 ## Content

 On the first screen user will see list of pokemon names, by scrolling down more pokemon names will show up. 
 By clicking on a pokemon name user goes to second screen with info about this pokemon, where user can see pokemon's name, type and abilities. 
 Click on ability to get more information about it. Then user can click on type to get its damage relations with other types and get filtered by this type pokemon list.

 ## How it works

On the first screen list of 20 pokemon names in alphabetical order is downloaded from Api, by scrolling down more lists are downloaded and all of these names are saved in database, so it wont be downloaded on subsequent launches.
When clicking on pokemon name, this name is used in api request to get info for next screen. Then getting information about abilities and getting filtered list of pokemons are all diffrent requests.
