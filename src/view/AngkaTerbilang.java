/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Mr Sanak
 */
public class AngkaTerbilang {
    public String angkaTerbilang(int angka) {
        String[] bilangan = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};
        String hasil = "";
        if (angka < 12) {
            hasil = " " + bilangan[angka];
        } else if (angka < 20) {
            hasil = angkaTerbilang(angka - 10) + " Belas";
        } else if (angka < 100) {
            hasil = angkaTerbilang(angka / 10) + " Puluh" + angkaTerbilang(angka % 10);
        } else if (angka < 200) {
            hasil = " Seratus" + angkaTerbilang(angka - 100);
        } else if (angka < 1000) {
            hasil = angkaTerbilang(angka / 100) + " Ratus" + angkaTerbilang(angka % 100);
        } else if (angka < 2000) {
            hasil = " Seribu" + angkaTerbilang(angka - 1000);
        } else if (angka < 1000000) {
            hasil = angkaTerbilang(angka / 1000) + " Ribu" + angkaTerbilang(angka % 1000);
        } else if (angka < 1000000000) {
            hasil = angkaTerbilang(angka / 1000000) + " Juta" + angkaTerbilang(angka % 1000000);
        }
        return hasil;
    }
}
