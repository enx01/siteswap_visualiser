package com.tiago.siteswap.visualiser.siteswap_visualiser.model;

import java.util.ArrayList;

public class Sequence {
    ArrayList<Dot> content;
    int nbUniq;

    public Sequence(String sequence) {
        this.content = new ArrayList<>();
        this.nbUniq = sequence.length();

        int max = 0;
        for (int i = 0; i < sequence.length(); i++) {
            int chiffre = Character.getNumericValue(sequence.charAt(i));
            max = chiffre > max ? chiffre : max;
        }

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < sequence.length(); j++) {
                int chiffre = Character.getNumericValue(sequence.charAt(j));
                content.add(new Dot(chiffre));
            }
        }

        for (int i = 0; i < content.size(); i++) {
            Dot d = content.get(i);
            d.destination = i+d.num > content.size()-1 ? null : content.get(i+d.num);
        }

    }

    public int getSize() { return content.size(); }

    public int getNbUniq() { return nbUniq; }

    public ArrayList<Dot> getContent() {
        return content;
    }

    public void print() {
        for (Dot d:
                content) {
            System.out.print(d.num + " ");
        }
    }

    public boolean checkDuplicates() {
        for (int i = 0; i < content.size(); i++) {
            Dot dotA = content.get(i);
            Dot destinationA = dotA.getDestination();
            for (int j = i + 1; j < content.size(); j++) {
                Dot dotB = content.get(j);
                Dot destinationB = dotB.getDestination();
                if (destinationA != null && destinationA == destinationB) {
                    return true;
                }
            }
        }
        return false;
    }
}