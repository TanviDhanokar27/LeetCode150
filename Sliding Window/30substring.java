class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
    int wordLength = words[0].length(); // since all words are equal length
    int totalWordLength = wordLength * words.length;
    //length of input string, used as upperbound.
    int n = s.length();

    List<Integer> result = new ArrayList<>();

    // Word frequency map
    Map<String, Integer> wordsWithCount = new HashMap<>();

    for (String word : words) {
        wordsWithCount.put(word, wordsWithCount.getOrDefault(word, 0) + 1);
    }

    for (int offset = 0; offset < wordLength; offset++) {
        int start = offset;
        Map<String, Integer> seenMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        while (start + wordLength <= n) {
            String currentWord = s.substring(start, start + wordLength);
            
            if (wordsWithCount.containsKey(currentWord)) {
                // Adding word to queue and updating seenMap.
                queue.offer(currentWord);
                seenMap.put(currentWord, seenMap.getOrDefault(currentWord, 0) + 1);

                while (seenMap.get(currentWord) > wordsWithCount.get(currentWord)) {
                    String removedWord = queue.poll();
                    seenMap.put(removedWord, seenMap.get(removedWord) - 1);
                }

                if (queue.size() == words.length) {
                    result.add(start - totalWordLength + wordLength);
                }
            } else {
                queue.clear();
                seenMap.clear();
            }

            start += wordLength;
        }
    }

    return result;      
    }
}