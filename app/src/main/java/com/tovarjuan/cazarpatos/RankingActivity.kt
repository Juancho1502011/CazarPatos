package com.tovarjuan.cazarpatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RankingActivity : AppCompatActivity() {
    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_ranking)
//
//        var jugadores = arrayListOf<Jugador>()
//        jugadores.add(Jugador("Jugador1",10))
//        jugadores.add(Jugador("Jugador2",6))
//        jugadores.add(Jugador("Jugador3",3))
//        jugadores.add(Jugador("Jugador4",9))
//        jugadores.sortByDescending { it.patosCazados }
//
//        val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewRanking);
//        recyclerViewRanking.layoutManager = LinearLayoutManager(this);
//        recyclerViewRanking.adapter = RankingAdapter(jugadores);
//        recyclerViewRanking.setHasFixedSize(true);
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        OperacionesSqLite()
        GrabarRankingSQLite()
        LeerRankingsSQLite()
    }

    fun OperacionesSqLite() {
        RankingPlayerDBHelper(this).deleteAllRanking()
        RankingPlayerDBHelper(this).insertRankingByQuery(Jugador("Jugador9", 10))
        val patosCazados = RankingPlayerDBHelper(this).readDucksHuntedByPlayer("Jugador9")
        RankingPlayerDBHelper(this).updateRanking(Jugador("Jugador9", 5))
        RankingPlayerDBHelper(this).deleteRanking("Jugador9")
        RankingPlayerDBHelper(this).insertRanking(Jugador("Jugador9", 7))
        val players = RankingPlayerDBHelper(this).readAllRankingByQuery()
    }

    fun GrabarRankingSQLite() {
        val jugadores = arrayListOf(
            Jugador("Jugador1", 11),
            Jugador("Jugador2", 6),
            Jugador("Jugador3", 3),
            Jugador("Jugador4", 9)
        )
        jugadores.sortByDescending { it.patosCazados }
        for (jugador in jugadores) {
            RankingPlayerDBHelper(this).insertRanking(jugador)
        }
    }

    fun LeerRankingsSQLite() {
        val jugadoresSQLite = RankingPlayerDBHelper(this).readAllRanking()
        val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewRanking)
        recyclerViewRanking.layoutManager = LinearLayoutManager(this)
        recyclerViewRanking.adapter = RankingAdapter(jugadoresSQLite)
        recyclerViewRanking.setHasFixedSize(true)
    }

}
