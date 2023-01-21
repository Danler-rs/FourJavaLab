package FoursLab;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    static String filename = "/home/ilya/IdeaProjects/Labs/src/FoursLab/lenin.txt";
    static String outPutFileName = "/home/ilya/IdeaProjects/Labs/src/FoursLab/outPut.txt";

    static String outPutEveryCharacterFileName = "/home/ilya/IdeaProjects/Labs/src/FoursLab/outPutEveryCharacter.txt";

    static String outPutEveryWordFileName = "/home/ilya/IdeaProjects/Labs/src/FoursLab/outPutEveryWord.txt";

    public static void main(String[] args) throws IOException {
        // Очищаем файл после предыдущих запусков проги
        clearTheOutPutFile(outPutFileName);
        clearTheOutPutFile(outPutEveryWordFileName);
        clearTheOutPutFile(outPutEveryCharacterFileName);

        List<String> listOfWordsInLeninFile = new ArrayList<>();
        List<String> listOfWordsWithoutDubls = new ArrayList<>();
        List<String> listOfEveryWordPlusItsCount = new ArrayList<>();
        List<String> listOfCharacterPlusItsCount = new ArrayList<>();

        listOfWordsInLeninFile = getWords(filename);





        // Первые задания //
        //listOfWordsWithoutDubls = removeDubls(listOfWordsInLeninFile);

        // Сортируем в порядке убывания в лексикографическом порядке
        //listOfWordsWithoutDubls.sort(Comparator.naturalOrder());

        // Оставляем слова от 4 до 7 символов
        //holdWordsFrom4To7Letters(listOfWordsWithoutDubls);

        //Запись оригинальных слов в файл
        //writeResultToFile(outPutFileName, listOfWordsWithoutDubls);
        //-----------------------------------------------------------


        // Вторые задания //

        //Сортируем и считаем кол-во исп. каждого слова без учета регистра
        //listOfWordsInLeninFile.sort(Comparator.naturalOrder());

        //для слов от 4 до 7 букв и в порядке возрастания
        //listOfEveryWordPlusItsCount = countEveryWord(listOfWordsInLeninFile);
        //listOfEveryWordPlusItsCount.sort(Comparator.naturalOrder());
        //writeResultToFile(outPutEveryWordFileName, listOfEveryWordPlusItsCount);
        //------------------------------------------------------------------------

        // Пятые задания + 4 //
        listOfWordsInLeninFile.sort(Comparator.naturalOrder());
        //listOfCharacterPlusItsCount = countEveryChars(listOfWordsInLeninFile);
        //writeResultToFile(outPutEveryCharacterFileName, listOfCharacterPlusItsCount);
        //countTotalChars(listOfWordsInLeninFile);

    }



    private static List<String> getWords (String source) throws IOException{
        String input = " ";
        var splitter = Pattern.compile("[\\p{Punct}\\d\\s«…»–]+");
        Matcher m = splitter.matcher(input);
        return Files.lines(Path.of(filename)).flatMap(splitter::splitAsStream).filter(w -> ! w.isEmpty()).collect(Collectors.toList());
    }

    private static void writeResultToFile(String filename, List<String> lines) throws IOException{
        Files.write(Path.of(filename), lines);
    }

    private static List<String> removeDubls(List<String> listOfWords){
        Set<String> set = new LinkedHashSet<>(listOfWords);
        List<String> list = new ArrayList<>(set);
        return list;
    }

    private static void clearTheOutPutFile(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename, false);
        PrintWriter printWriter = new PrintWriter(fileWriter, false);
        printWriter.flush();
        printWriter.close();
        fileWriter.close();
    }

    private static List<String> holdWordsFrom4To7Letters(List<String> listOfWords){
        listOfWords.removeIf(s -> s.length() > 7);
        listOfWords.removeIf(s -> s.length() < 4);
        return listOfWords;
    }


    private static List<String> replace(List<String> strings){
        List<String> resultList = new ArrayList<>(strings);
        ListIterator<String> iterator = resultList.listIterator();
        while (iterator.hasNext())
        {
            iterator.set(iterator.next().toLowerCase());
        }
        return resultList;
    }



    private static List<String> countEveryWord(List<String> listOfWords) {


        Map<String, Integer> word_count_map = new HashMap<>();
        //Список для слов в нижнем регистре
        List<String> newList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();


        // В нижний регистр
        newList = replace(listOfWords);


        // Для 2 задания 4-7 символов убрать комментарий
        //holdWordsFrom4To7Letters(newList);


        for (String word:
             newList) {
            if (word_count_map.containsKey(word)){
                word_count_map.put(word, word_count_map.get(word) + 1);
            } else {
                word_count_map.put(word, 1);
            }
        }




        // Преобразуем map в List для печати в файл
        List<String> keyList = new ArrayList<>(word_count_map.keySet());
        List<Integer> valueList = new ArrayList<>(word_count_map.values());

        for (int i = 0; i < keyList.size(); i++){
            String result = keyList.get(i) +" - " + valueList.get(i);
            resultList.add(result);
        }


        return resultList;
    }

    private static List<String> countEveryChars(List<String> listOfWords){
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        List<String> characterList = new ArrayList<>();

        for (String word:listOfWords) {
            for (int i = 0; i < word.length(); i++){
                if (characterIntegerMap.containsKey(word.charAt(i))){
                    characterIntegerMap.put(word.charAt(i), characterIntegerMap.get(word.charAt(i)) + 1);
                }else {
                    characterIntegerMap.put(word.charAt(i), 1);
                }
            }
        }

        // Преобразуем map в List для печати в файл
        List<Character> keyList = new ArrayList<>(characterIntegerMap.keySet());
        List<Integer> valueList = new ArrayList<>(characterIntegerMap.values());

        for (int i = 0; i < keyList.size(); i++){
            String result = keyList.get(i) +" - " + valueList.get(i);
            characterList.add(result);
        }

        return characterList;

    }

    private static void countTotalChars(List<String> listOfWords){
        Map<Character, Integer> characterIntegerMap = new HashMap<>();

        for (String word:listOfWords) {
            for (int i = 0; i < word.length(); i++){
                if (characterIntegerMap.containsKey(word.charAt(i))){
                    characterIntegerMap.put(word.charAt(i), characterIntegerMap.get(word.charAt(i)) + 1);
                }else {
                    characterIntegerMap.put(word.charAt(i), 1);
                }
            }
        }
        int total = 0;
        for (Map.Entry<Character, Integer> entry : characterIntegerMap.entrySet()) {
            total += entry.getValue();
        }


        System.out.println("Всего символов в тексте " + total);


    }

}
