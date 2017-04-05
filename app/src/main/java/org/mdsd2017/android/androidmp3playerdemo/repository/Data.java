package org.mdsd2017.android.androidmp3playerdemo.repository;

import org.mdsd2017.android.androidmp3playerdemo.R;
import org.mdsd2017.android.androidmp3playerdemo.models.Song;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hilda on 04/04/17.
 */

public class Data {

    private final static List<Song> PLAYLIST = new ArrayList<>(Arrays.asList(
        new Song("Brazil Samba", R.raw.bensoundbrazilsamba, "Brazil", 240, R.drawable.samba, "Samba is a Brazilian musical genre and dance style, with its roots in Africa via the West African slave trade and African religious traditions, particularly of Angola."),
        new Song("Country boy", R.raw.bensoundcountryboy, "USA", 207, R.drawable.country, "Country music is a genre of American popular music that originated in the Southern United States in the 1920s."),
        new Song("India", R.raw.bensoundindia, "India", 253, R.drawable.india, "The music of India includes multiple varieties of folk music, pop, and Indian classical music. India's classical music tradition, including Hindustani music and Carnatic, has a history spanning millennia and developed over several eras."),
        new Song("Little planet", R.raw.bensoundlittleplanet, "Iceland", 416, R.drawable.iceland, "The music of Iceland includes vibrant folk and pop traditions. Well-known artists from Iceland include medieval music group Voces Thules, alternative rock band The Sugarcubes, singers Björk and Emiliana Torrini, post-rock band Sigur Rós and indie folk/indie pop band Of Monsters and Men."),
        new Song("Psychedelic", R.raw.bensoundpsychedelic, "South Korea", 236, 0, "The Music of South Korea has evolved over the course of the decades since the end of the Korean War, and has its roots in the music of the Korean people, who have inhabited the Korean peninsula for over a millennium. Contemporary South Korean music can be divided into three different main categories: Traditional Korean folk music, popular music, or K-pop, and Western-influenced non-popular music."),
        new Song("Relaxing", R.raw.bensoundrelaxing, "Indonesia", 288, 0, "The music of Indonesia demonstrates its cultural diversity, the local musical creativity, as well as subsequent foreign musical influences that shaped contemporary music scenes of Indonesia. Nearly thousands of Indonesian islands having its own cultural and artistic history and character."),
        new Song("The elevator Bossa Nova", R.raw.bensoundtheelevatorbossanova, "Brazil", 254, R.drawable.bossanova, "Samba is a Brazilian musical genre and dance style, with its roots in Africa via the West African slave trade and African religious traditions, particularly of Angola.")
    ));
    private static Data mInstance;

    private Data() {

    }

    public static Data newInstance(){
        if(mInstance == null){
            mInstance = new Data();
        }

        return mInstance;
    }

    public List<Song> getSongs(){

        return PLAYLIST;
    }

    public Song getFirst(){

        return PLAYLIST.get(0);
    }

    public Song getNext(Song currentSong){
        int currentIndex = PLAYLIST.indexOf(currentSong);
        if (currentIndex == PLAYLIST.size()-1){
            return PLAYLIST.get(0);
        }

        return PLAYLIST.get(currentIndex+1);
    }

    public Song getPrev(Song currentSong){
        int currentIndex = PLAYLIST.indexOf(currentSong);
        if (currentIndex == 0){
            return PLAYLIST.get(PLAYLIST.size()-1);
        }

        return PLAYLIST.get(currentIndex-1);
    }

}
