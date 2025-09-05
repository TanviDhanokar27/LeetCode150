class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
                int n = words.length;
        int[] len = new int[n];
        int i = 0;

        for (String str : words) {
            len[i++] = str.length();
        }

        List<String> ans = new ArrayList<>();
        int start = 0;
        int end = 0;
        int cnt = 0;

        for (int x = 0; x < n; x++) {
            if (cnt + len[x] + (end - start) > maxWidth) {
                ans.add(func(start, end - 1, cnt, words, maxWidth));
                cnt = 0;
                start = x;
            }
            cnt += len[x];
            end = x + 1;
        }

        // handle the last line (left-justified)
        StringBuilder lastLine = new StringBuilder();
        for (int j = start; j < end; j++) {
            lastLine.append(words[j]);
            if (j < end - 1) lastLine.append(" ");
        }
        while (lastLine.length() < maxWidth) lastLine.append(" ");
        ans.add(lastLine.toString());

        return ans;
    }

    private String func(int s, int e, int cnt, String[] arr, int maxWidth) {
        int spaces = maxWidth - cnt;
        int number_of_words = e - s;

        if (number_of_words == 0) {
            StringBuilder sb = new StringBuilder(arr[s]);
            while (sb.length() < maxWidth) sb.append(" ");
            return sb.toString();
        }

        int sp = spaces / number_of_words;
        int ex = spaces % number_of_words;
        StringBuilder sb = new StringBuilder();
        
        while (s < e) {
            sb.append(arr[s]);
            sb.append(" ");
            for (int x = 1; x < sp; x++) {
                sb.append(" ");
            }
            if (ex != 0) {
                sb.append(" ");
                ex--;
            }
            s++;
        }
        sb.append(arr[e]);

        return sb.toString();
    }
}