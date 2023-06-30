package com.example.dispositivosmoviles.data

import com.example.dispositivosmoviles.ui.activities.entity.jkanmarvel.LoginMarvel

class ListMarvel {

    fun retunrItems(): List<LoginMarvel>{
        var items = listOf(
            LoginMarvel(
                1,
                "Spider-Man",
                "The Amazing Spider-Man Vol.7 (2014)",
                "https://comicvine.gamespot.com/a/uploads/scale_small/6/67663/3493707-700.3a.jpg"
            ),
            LoginMarvel(
                2,
                "Black Panther",
                "Black Panther (2003)",
                "https://comicvine.gamespot.com/a/uploads/scale_small/2/26954/659356-5395_20060926192902_large.jpg"
            ),
            LoginMarvel(
                3,
                "Black Cat",
                "The Amazing Spider-Man Vol.5 (2012)",
                "https://comicvine.gamespot.com/a/uploads/scale_small/11144/111442876/8759849-grr.jpg"
            ),
            LoginMarvel(
                4,
                "Spider-Woman",
                "The Amazing Spider-Man Vol.3 (2005)",
                "https://comicvine.gamespot.com/a/uploads/scale_small/11116/111167641/7822219-spiderwoman_issue11-art.jpg"
            ),
            LoginMarvel(
                5,
                "Blade",
                "Blade (2007)",
                "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/7949396-heroes_reborn_vol_2_1_devil_dog_comics_exclusive_virgin_variant.jpg"
            ),

            )

        return items
    }
}