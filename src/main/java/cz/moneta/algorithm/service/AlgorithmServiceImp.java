package cz.moneta.algorithm.service;

import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class AlgorithmServiceImp implements AlgorithmService {
    @Override
    public String getNumber(String number) {
        System.out.println("Vstupni hodnota:" + number);
        Integer.parseInt(number);
        char[] charArray = number.toCharArray();
        int[] cisloCisliceMensiTriPosunutaDoprava = posunVsechnyCisliceMensiTriDoprava(charArray);
        int[] cisloCisliceDevetAOsumVysanosobenaDvema = vynasobVsechnyCisliceOsmADevetDvema(cisloCisliceMensiTriPosunutaDoprava);
        int[] cisloCisliceSedmSmazana = smazVsechnyCisliceSedm(cisloCisliceDevetAOsumVysanosobenaDvema);
        int pocetSudychCislic = getSudychCislic(cisloCisliceSedmSmazana);
        String finalNumber = IntStream.of(cisloCisliceSedmSmazana).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        int vysledek = 0;
        if (pocetSudychCislic == 0) {
            vysledek = Integer.parseInt(finalNumber);
        } else {
            vysledek = Integer.parseInt(finalNumber) / pocetSudychCislic;
        }

        System.out.println("Vysledek: " + vysledek);
        return String.valueOf(vysledek);
    }

    private int getSudychCislic(int[] number) {
        int countEven = 0;
        for (int i = 0; i < number.length; i++) {
            if (number[i] % 2 == 0) {
                countEven++;
            }
        }
        System.out.println("Pocet sudych: " + countEven);
        return countEven;
    }

    private int[] smazVsechnyCisliceSedm(int[] number) {
        int[] temp = new int[number.length];
        int countSevenDigits = 0;
        for (int i = 0; i < number.length; i++) {
            if (number[i] != 7) {
                temp[i - countSevenDigits] = number[i];
            } else {
                countSevenDigits++;
            }

        }
        int[] temp2 = new int[number.length - countSevenDigits];
        System.arraycopy(temp, 0, temp2, 0, temp2.length);
        String finalNumber = IntStream.of(temp2).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        System.out.println("Smaz vsechny cislice sedm:" + finalNumber);
        return temp2;
    }

    private int[] vynasobVsechnyCisliceOsmADevetDvema(int[] number) {
        int[] temp = new int[number.length];
        for (int i = 0; i < number.length; i++) {
            if (number[i] == 8 || number[i] == 9) {
                temp[i] = number[i] * 2;
            } else {
                temp[i] = number[i];
            }
        }
        String finalNumber = IntStream.of(temp).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        System.out.println("Vynasob dvema:" + finalNumber);
        return temp;
    }

    private int[] posunVsechnyCisliceMensiTriDoprava(char[] charArray) {
        int[] temp = new int[charArray.length];
        for (int i = charArray.length - 1; i >= 0; i--) {
            if (i == charArray.length - 1) {
                temp[i] = Character.getNumericValue(charArray[i]);
            } else {
                if (i == 0) {
                    temp[i] = Character.getNumericValue(charArray[i]);
                } else {
                    int currentNumber = Character.getNumericValue(charArray[i - 1]);
                    if (currentNumber <= 3) {
                        temp[i] = currentNumber;
                        temp[i - 1] = Character.getNumericValue(charArray[i]);
                        charArray[i - 1] = Character.forDigit(temp[i - 1], 10);
                    } else {
                        temp[i] = Character.getNumericValue(charArray[i]);
                    }
                }
            }
        }

        String finalNumber = IntStream.of(temp).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        System.out.println("Posun do prava:" + finalNumber);
        return temp;
    }
}
