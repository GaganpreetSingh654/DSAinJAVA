// when you look up a word in a dictionary, you don't start from page 1 and flip one page at a time. You open somewhere in the middle, see if your word comes before or after that page alphabetically, and then throw away the entire half you know it can't be in. Then you repeat on the remaining half.

// Every time you do this, you cut your search space in half. That's the entire trick behind binary search, and it's why it's so much faster than linear search.

// Requirement: this only works because a dictionary is sorted alphabetically. If the pages were shuffled randomly, glancing at one page would tell you nothing about where your word is — you'd be back to checking every page one by one. That's exactly why binary search requires a sorted array.

// Why O(log n) instead of O(n)

// Linear search (checking one element at a time):

// Worst case, you check every single element → if there are n elements, that's n checks → O(n)

// Binary search (halving each time):

// Each check eliminates half of what's left
// Starting from n elements: n → n/2 → n/4 → n/8 → ... until you're down to 1 element
// The number of times you can halve n before reaching 1 is log₂(n)

// Concrete comparison — say you have 1,000,000 sorted elements:

// Linear search: up to 1,000,000 checks
// Binary search: only about 20 checks (log₂(1,000,000) ≈ 20)

// That's the difference between checking every page in an enormous dictionary versus needing only ~20 flips to land on any word, no matter how thick the dictionary is. This gap grows explosively as the data gets bigger — that's what makes binary search so valuable for large datasets.

// The three pointers that drive it

// Binary search tracks a shrinking window using two boundaries:

// left — the leftmost index still worth checking
// right — the rightmost index still worth checking
// mid — the midpoint of that window, recalculated every round

// At every step you ask one question: "Is my target at mid, to the left of mid, or to the right of mid?"

// If nums[mid] == target → found it, done.
// If nums[mid] < target → target must be further right (since the array is sorted, everything at or before mid is too small) → move left = mid + 1
// If nums[mid] > target → target must be further left → move right = mid - 1

// You repeat this until either you find the target, or left crosses over right (meaning the window has shrunk to nothing — the target isn't in the array).

public class BinarySearch {
 static int binarySearch1(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            /* why we mid calculate like this insent of this ( right + left ) /2 = because we my start and end had bid number 
             we add then we cross limit of int so we first subtract so we get small number then we divide we get samll number then add subtract one last    */

            if (nums[mid] == target) {
                return mid;
            } else if ( target > nums[mid]) {
                start = mid + 1;   // discard left half, including mid
            } else {
                end = mid - 1;  // discard right half, including mid
            }
        }

        return -1;  // target doesn't exist in array
    }
        public static void main(String[] args) {
        int[] nums = {2, 4, 6, 8, 10, 12, 14};
        int target = 6;

        int result = binarySearch1(nums, target);

        if (result != -1) {
            System.out.println("Found at index: " + result);
        } else {
            System.out.println("Not found");
        }
}
}
// Full dry run, step by step

// Array: {2, 4, 6, 8, 10, 12, 14} (indices 0–6), target = 6

// Step	left	right	mid	nums[mid]	Comparison	Action
// 1	0	6	3	8	8 > 6	target is left → right = 2
// 2	0	2	1	4	4 < 6	target is right → left = 2
// 3	2	2	2	6	6 == 6	Found at index 2 ✅

// Just 3 comparisons instead of checking indices 0, 1, 2 one by one with linear search. On a bigger array the savings become dramatic — that's the whole point.

// What happens when the target ISN'T in the array

// Say target = 5 (doesn't exist):

// Step	left	right	mid	nums[mid]	Action
// 1	0	6	3	8	8 > 5 → right = 2
// 2	0	2	1	4	4 < 5 → left = 2
// 3	2	2	2	6	6 > 5 → right = 1
// —	2	1	—	—	left > right → loop ends

// The loop condition left <= right catches this: once left overtakes right, there's no window left to search, so we exit and return -1. This is the same as flipping through a dictionary and realizing you've narrowed down to zero pages left — the word simply isn't there.

// Space complexity: O(1)

// Unlike some algorithms that need extra arrays or recursion stacks, this iterative version only ever uses three variables (left, right, mid) no matter how big the input array is. That's constant space.

// (Note: a recursive version of binary search would use O(log n) space instead, because each recursive call adds a frame to the call stack — one frame per halving. The iterative version above avoids that entirely.)

// The one rule that makes or breaks binary search

// The array must be sorted. If it isn't, binary search will silently give wrong answers instead of erroring out — because the "target is left or right of mid" logic completely depends on sorted order. This is the single most common bug people hit: running binary search on unsorted data and getting confusing, inconsistent results.