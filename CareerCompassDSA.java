// ============================================================
// FILE NAME  : CareerCompassDSA.java
// PROJECT    : Career Compass for Student Dreams
// AUTHOR     : M. Mohana Asmitha | Roll: 2520030486
// HOW TO RUN :
//   1. Open this file in VS Code
//   2. Make sure Java JDK is installed
//   3. Open Terminal in VS Code  (Ctrl + `)
//   4. Compile :  javac CareerCompassDSA.java
//   5. Run     :  java  CareerCompassDSA
// ============================================================
//
// CO1 - Searching & Sorting : Linear Search, Binary Search,
//                             Bubble Sort, Selection Sort
// CO2 - Lists (ADT)         : ArrayList (careers, courses, skills),
//                             Manual Singly LinkedList (vocabulary)
// CO3 - Stacks & Queues     : Stack  - Quiz review + Confidence tips
//                             Queue  - Resume steps + Vocab practice
// CO4 - Hashing             : HashMap - career store, user store,
//                             resume store, skill-to-career mapping
//
// ============================================================

import java.util.*;

// ============================================================
// CO2 - Career class  (stored in ArrayList and HashMap)
// ============================================================
class Career {
    String name;
    int demand, growth, stability, salary;
    String[] topSkills;

    Career(String name, int demand, int growth, int stability,
           int salary, String[] topSkills) {
        this.name      = name;
        this.demand    = demand;
        this.growth    = growth;
        this.stability = stability;
        this.salary    = salary;
        this.topSkills = topSkills;
    }

    public String shortLine() {
        return String.format("  %-28s | Demand:%3d | Growth:%3d | Stability:%3d | Salary:%3d LPA",
                name, demand, growth, stability, salary);
    }

    public void printDetail() {
        System.out.println("  +-----------------------------------------------+");
        System.out.printf ("  |  Career     : %-31s|%n", name);
        System.out.printf ("  |  Demand     : %-3d / 100                       |%n", demand);
        System.out.printf ("  |  Growth     : %-3d / 100                       |%n", growth);
        System.out.printf ("  |  Stability  : %-3d / 100                       |%n", stability);
        System.out.printf ("  |  Avg Salary : %-3d LPA                         |%n", salary);
        System.out.printf ("  |  Top Skills : %-31s|%n", String.join(", ", topSkills));
        System.out.println("  +-----------------------------------------------+");
    }
}

// ============================================================
// CO2 - Course class  (stored in ArrayList)
// ============================================================
class Course {
    String title, category, platform;
    int duration;

    Course(String title, String category, String platform, int duration) {
        this.title    = title;
        this.category = category;
        this.platform = platform;
        this.duration = duration;
    }

    public String toString() {
        return String.format("  %-38s [%-15s] %-10s  %2d weeks",
                title, category, platform, duration);
    }
}

// ============================================================
// CO2 - Node class for manual Singly LinkedList (vocab words)
// ============================================================
class VocabNode {
    String word, meaning;
    VocabNode next;
    VocabNode(String word, String meaning) {
        this.word    = word;
        this.meaning = meaning;
        this.next    = null;
    }
}

// ============================================================
// CO2 - Manual Singly LinkedList for FluentRise English vocab
// ============================================================
class VocabLinkedList {
    VocabNode head = null;
    int size = 0;

    // CO2: insert at end of list
    void add(String word, String meaning) {
        VocabNode node = new VocabNode(word, meaning);
        if (head == null) { head = node; }
        else {
            VocabNode cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = node;
        }
        size++;
    }

    // CO2: traverse and print all nodes
    void printAll() {
        VocabNode cur = head;
        int i = 1;
        while (cur != null) {
            System.out.printf("  %2d. %-18s -> %s%n", i++, cur.word, cur.meaning);
            cur = cur.next;
        }
    }

    // CO1: linear search inside linked list
    boolean search(String word) {
        VocabNode cur = head;
        int steps = 0;
        while (cur != null) {
            steps++;
            if (cur.word.equalsIgnoreCase(word)) {
                System.out.println("      FOUND \"" + word + "\" at node " + steps);
                System.out.println("      Meaning: " + cur.meaning);
                return true;
            }
            cur = cur.next;
        }
        System.out.println("      \"" + word + "\" not found after " + steps + " steps.");
        return false;
    }
}

// ============================================================
// MAIN CLASS
// ============================================================
public class CareerCompassDSA {

    // CO2: ArrayLists
    static ArrayList<Career> careerList         = new ArrayList<>();
    static ArrayList<Career> selectedCareers    = new ArrayList<>();
    static ArrayList<Course> courseList         = new ArrayList<>();
    static ArrayList<String> skillPool          = new ArrayList<>();

    // CO4: HashMaps
    static HashMap<String, Career>       careerStore = new HashMap<>();
    static HashMap<String, String>       userStore   = new HashMap<>();
    static HashMap<String, String>       resumeStore = new HashMap<>();
    static HashMap<String, List<String>> skillMap    = new HashMap<>();

    // CO4: HashMap for registered users (username -> password)
    static HashMap<String, String> loginStore = new HashMap<>();

    // CO2: Manual LinkedList for vocabulary
    static VocabLinkedList vocabList = new VocabLinkedList();

    static Scanner sc = new Scanner(System.in);

    // ============================================================
    // LOGIN PAGE
    // ============================================================
    static boolean showLoginPage() {
        // Pre-load some demo accounts
        loginStore.put("admin",   "admin123");
        loginStore.put("student", "pass@123");
        loginStore.put("asmitha", "career2025");

        System.out.println("================================================================");
        System.out.println("         CAREER COMPASS FOR STUDENT DREAMS");
        System.out.println("              ** LOGIN / REGISTER PAGE **");
        System.out.println("================================================================");
        System.out.println("  +----------------------------------------------------------+");
        System.out.println("  |                                                          |");
        System.out.println("  |        Welcome to Career Compass!                       |");
        System.out.println("  |        Your smart guide to your dream career.           |");
        System.out.println("  |                                                          |");
        System.out.println("  |   [1]  LOGIN    - Already have an account               |");
        System.out.println("  |   [2]  REGISTER - Create a new account                  |");
        System.out.println("  |   [3]  EXIT     - Quit application                      |");
        System.out.println("  |                                                          |");
        System.out.println("  +----------------------------------------------------------+");

        int maxAttempts = 3;

        while (true) {
            System.out.print("\n  Choose option [1/2/3]: ");
            String option = sc.nextLine().trim();

            switch (option) {
                case "1":
                    // --- LOGIN FLOW ---
                    System.out.println("\n  +------ LOGIN ------+");
                    int attempts = 0;
                    while (attempts < maxAttempts) {
                        System.out.print("  Username : ");
                        String uname = sc.nextLine().trim();
                        System.out.print("  Password : ");
                        String pwd   = sc.nextLine().trim();

                        if (loginStore.containsKey(uname) && loginStore.get(uname).equals(pwd)) {
                            System.out.println("\n  +----------------------------------------------------------+");
                            System.out.println("  |   LOGIN SUCCESSFUL!  Welcome, " + uname + "!");
                            System.out.println("  |   Redirecting to Career Compass...                      |");
                            System.out.println("  +----------------------------------------------------------+\n");
                            return true;  // login OK, proceed
                        } else {
                            attempts++;
                            System.out.println("  !! Invalid username or password. Attempt " + attempts + "/" + maxAttempts);
                            if (attempts < maxAttempts)
                                System.out.println("     Please try again.\n");
                        }
                    }
                    System.out.println("  !! Too many failed attempts. Returning to menu.\n");
                    // loop back to main menu after 3 failures
                    showLoginPage();
                    return false;

                case "2":
                    // --- REGISTER FLOW ---
                    System.out.println("\n  +------ REGISTER ------+");
                    System.out.print("  Choose a Username  : ");
                    String newUser = sc.nextLine().trim();

                    if (loginStore.containsKey(newUser)) {
                        System.out.println("  !! Username \"" + newUser + "\" already exists. Try logging in.");
                        break;
                    }

                    System.out.print("  Choose a Password  : ");
                    String newPwd  = sc.nextLine().trim();
                    System.out.print("  Confirm Password   : ");
                    String conPwd  = sc.nextLine().trim();

                    if (!newPwd.equals(conPwd)) {
                        System.out.println("  !! Passwords do not match. Please try again.");
                        break;
                    }

                    if (newPwd.length() < 6) {
                        System.out.println("  !! Password must be at least 6 characters.");
                        break;
                    }

                    loginStore.put(newUser, newPwd);
                    System.out.println("\n  +----------------------------------------------------------+");
                    System.out.println("  |  REGISTRATION SUCCESSFUL!  Hello, " + newUser + "!");
                    System.out.println("  |  Please login with your new credentials.                |");
                    System.out.println("  +----------------------------------------------------------+\n");
                    // After register, show login again
                    break;

                case "3":
                    System.out.println("\n  Goodbye! Thank you for using Career Compass.");
                    System.out.println("================================================================");
                    sc.close();
                    System.exit(0);
                    return false;

                default:
                    System.out.println("  !! Invalid option. Please enter 1, 2, or 3.");
            }
        }
    }

    // ============================================================
    public static void main(String[] args) {

        // --------------------------------------------------------
        // SHOW LOGIN PAGE FIRST - only continue if login passes
        // --------------------------------------------------------
        boolean loggedIn = showLoginPage();
        if (!loggedIn) return;

        banner("CAREER COMPASS FOR STUDENT DREAMS  -  JAVA DSA DEMO");

        loadCareers();
        loadCourses();
        loadSkills();
        loadVocab();

        // --------------------------------------------------------
        // STEP 1: Collect user details via Scanner input
        // --------------------------------------------------------
        section("USER DETAILS : Please enter your information");
        System.out.print("  Enter Full Name       : ");
        String fullName = sc.nextLine().trim();

        System.out.print("  Enter Phone Number    : ");
        String phone = sc.nextLine().trim();

        System.out.print("  Enter Email           : ");
        String email = sc.nextLine().trim();

        System.out.print("  Enter College Name    : ");
        String college = sc.nextLine().trim();

        System.out.print("  Enter Branch          : ");
        String branch = sc.nextLine().trim();

        System.out.print("  Enter Year            : ");
        String year = sc.nextLine().trim();

        System.out.print("  Enter CGPA            : ");
        String cgpa = sc.nextLine().trim();

        System.out.print("  Enter Skills (comma-separated): ");
        String skills = sc.nextLine().trim();

        System.out.print("  Enter Project Name    : ");
        String project = sc.nextLine().trim();

        System.out.print("  Enter Certification   : ");
        String cert = sc.nextLine().trim();

        // --------------------------------------------------------
        // STEP 2: Career Selection
        // --------------------------------------------------------
        section("CAREER SELECTION : Choose careers you are interested in");
        System.out.println("  Available Careers:");
        for (int i = 0; i < careerList.size(); i++)
            System.out.printf("  [%2d] %s%n", i + 1, careerList.get(i).name);

        System.out.println("\n  Enter career numbers separated by commas (e.g. 1,3,5)");
        System.out.print("  Your choices: ");
        String choiceInput = sc.nextLine().trim();
        String[] choices = choiceInput.split(",");

        for (String ch : choices) {
            try {
                int idx = Integer.parseInt(ch.trim()) - 1;
                if (idx >= 0 && idx < careerList.size()) {
                    selectedCareers.add(careerList.get(idx));
                }
            } catch (NumberFormatException ignored) {}
        }

        System.out.println("\n  You selected " + selectedCareers.size() + " career(s):");
        for (Career c : selectedCareers) System.out.println("   -> " + c.name);

        // Now populate HashMaps with user-entered data
        loadHashMaps(fullName, phone, email, college, branch, year, cgpa, skills, project, cert);

        // ========================================================
        // CO1 - LINEAR SEARCH  (on selected careers)
        // ========================================================
        section("CO1 - LINEAR SEARCH : Find Career by Name");
        if (!selectedCareers.isEmpty()) {
            for (Career sel : selectedCareers)
                linearSearchCareer(sel.name);
        } else {
            System.out.println("  No careers selected to search.");
        }

        // ========================================================
        // CO1 - BUBBLE SORT  (salary high to low)
        // ========================================================
        section("CO1 - BUBBLE SORT : Sort Selected Careers by Salary (High to Low)");
        if (selectedCareers.size() > 1) {
            System.out.println("  Before Sort:");
            for (Career c : selectedCareers) System.out.println(c.shortLine());
            bubbleSortBySalary();
            System.out.println("\n  After Bubble Sort (Salary High to Low):");
            for (Career c : selectedCareers) System.out.println(c.shortLine());
        } else {
            System.out.println("  Select more than 1 career to see sorting.");
            for (Career c : selectedCareers) System.out.println(c.shortLine());
        }

        // ========================================================
        // CO1 - SELECTION SORT  (demand high to low)
        // ========================================================
        section("CO1 - SELECTION SORT : Sort Selected Careers by Demand (High to Low)");
        if (selectedCareers.size() > 1) {
            selectionSortByDemand();
            System.out.println("  After Selection Sort (Demand High to Low):");
            for (Career c : selectedCareers) System.out.println(c.shortLine());
        } else {
            System.out.println("  Select more than 1 career to see sorting.");
        }

        // ========================================================
        // CO1 - BINARY SEARCH  (list sorted A to Z first)
        // ========================================================
        section("CO1 - BINARY SEARCH : Find Career by Name (list sorted A-Z)");
        sortByNameAZ();
        System.out.println("  Full career list sorted A to Z:");
        for (int i = 0; i < careerList.size(); i++)
            System.out.printf("  [%2d] %s%n", i, careerList.get(i).name);
        System.out.println();
        for (Career sel : selectedCareers)
            binarySearchCareer(sel.name);

        // ========================================================
        // CO1 - SEARCH IN LINKED LIST  (FluentRise vocab)
        // ========================================================
        section("CO1 - LINEAR SEARCH IN LINKED LIST : FluentRise Vocabulary");
        System.out.print("  Enter a vocab word to search (e.g. Eloquent): ");
        String vocabQuery = sc.nextLine().trim();
        vocabList.search(vocabQuery);

        // ========================================================
        // CO2 - ARRAYLIST : Courses
        // ========================================================
        section("CO2 - ARRAYLIST : All Courses Loaded");
        System.out.printf("  Total courses loaded: %d%n%n", courseList.size());
        System.out.printf("  %-38s %-17s %-10s  %s%n",
                "Title", "Category", "Platform", "Duration");
        System.out.println("  " + "-".repeat(78));
        for (Course c : courseList) System.out.println(c);

        // ========================================================
        // CO2 - ARRAYLIST : Skills Pool
        // ========================================================
        section("CO2 - ARRAYLIST : Skills Pool");
        System.out.println("  All " + skillPool.size() + " skills tracked in the system:");
        for (int i = 0; i < skillPool.size(); i++)
            System.out.printf("  %2d. %s%n", i + 1, skillPool.get(i));

        // ========================================================
        // CO2 - LINKED LIST : Vocabulary traversal
        // ========================================================
        section("CO2 - LINKED LIST : FluentRise Vocabulary (node-by-node traversal)");
        System.out.println("  Traversing all vocab nodes (head to tail):");
        vocabList.printAll();
        System.out.println("\n  LinkedList total size: " + vocabList.size + " word nodes");

        // ========================================================
        // CO2 - Career DETAIL CARDS (selected careers only)
        // ========================================================
        section("CO2 - CAREER DETAIL CARDS : Your Selected Careers");
        if (selectedCareers.isEmpty()) {
            System.out.println("  No careers selected.");
        } else {
            System.out.println("  Displaying " + selectedCareers.size() + " selected career card(s):\n");
            for (Career c : selectedCareers) c.printDetail();
        }

        // ========================================================
        // CO3 - STACK : Self-Assessment Quiz (LIFO review)
        // ========================================================
        section("CO3 - STACK : Self-Assessment Quiz Responses (LIFO)");
        Stack<String> quizStack = new Stack<>();
        String[] questions = {
            "Q1: I enjoy solving logical problems          -> Yes",
            "Q2: I prefer working with large data sets     -> Yes",
            "Q3: I like designing user interfaces          -> No",
            "Q4: I enjoy writing code every day            -> Yes",
            "Q5: I am comfortable with cloud platforms     -> Maybe",
            "Q6: I like to teach and mentor others         -> Yes",
            "Q7: I enjoy finding security vulnerabilities  -> Yes",
            "Q8: I prefer working in teams                 -> Yes",
            "Q9: I can work independently on projects      -> Yes",
            "Q10: I enjoy learning new technologies daily  -> Yes"
        };
        System.out.println("  PUSHING all quiz answers onto stack:");
        for (String q : questions) {
            quizStack.push(q);
            System.out.println("  PUSH -> " + q + "  [Stack size: " + quizStack.size() + "]");
        }
        System.out.println("\n  REVIEWING answers in reverse (LIFO):");
        int score = 0;
        while (!quizStack.isEmpty()) {
            String ans = quizStack.pop();
            System.out.println("  POP  <- " + ans);
            if (ans.contains("Yes")) score++;
        }
        System.out.println("\n  Quiz complete! Positive answers: " + score + "/" + questions.length);
        System.out.println("  Recommended career track: " +
                (score >= 7 ? "Technology / Data Science / AI" : "Design / Management / Teaching"));

        // ========================================================
        // CO3 - STACK : BoostUpConfidence Tips (LIFO display)
        // ========================================================
        section("CO3 - STACK : BoostUpConfidence Daily Tips (LIFO)");
        Stack<String> tipStack = new Stack<>();
        String[] tips = {
            "Tip 1: Write down 3 things you are grateful for today.",
            "Tip 2: Spend 10 minutes on deep breathing exercises.",
            "Tip 3: Complete one small task to build momentum.",
            "Tip 4: Avoid comparing your journey to others.",
            "Tip 5: Celebrate every small win - it builds confidence!",
            "Tip 6: Read one success story of a person you admire.",
            "Tip 7: Visualise yourself succeeding in your dream career.",
            "Tip 8: Practice speaking in front of a mirror for 5 minutes.",
            "Tip 9: Write one positive affirmation and repeat it 10 times."
        };
        System.out.println("  Loading confidence tips into stack:");
        for (String tip : tips) {
            tipStack.push(tip);
            System.out.println("  PUSH -> " + tip);
        }
        System.out.println("\n  Displaying today's top tip first (LIFO - most recent on top):");
        while (!tipStack.isEmpty())
            System.out.println("  <- POP: " + tipStack.pop());

        // ========================================================
        // CO3 - QUEUE : Resume Builder Steps (FIFO)
        // ========================================================
        section("CO3 - QUEUE : Resume Builder Steps (FIFO Processing)");
        Queue<String> resumeQueue = new LinkedList<>();
        String[] steps = {
            "Step 1: Enter Full Name & Contact Details",
            "Step 2: Add College / Education Background",
            "Step 3: List Technical & Soft Skills",
            "Step 4: Add Internships / Work Experience",
            "Step 5: Add Projects with GitHub Links",
            "Step 6: Add Certifications and Awards",
            "Step 7: Add Hobbies & Extra-Curriculars",
            "Step 8: Choose Resume Template",
            "Step 9: Preview Resume Live",
            "Step 10: Download Final PDF Resume"
        };
        System.out.println("  Enqueueing all resume build steps:");
        for (String s : steps) {
            resumeQueue.offer(s);
            System.out.println("  ENQUEUE -> " + s + "  [Queue size: " + resumeQueue.size() + "]");
        }
        System.out.println("\n  Processing steps in FIFO order:");
        int stepNo = 0;
        while (!resumeQueue.isEmpty()) {
            stepNo++;
            System.out.println("  DEQUEUE [" + stepNo + "] Done: " + resumeQueue.poll());
        }
        System.out.println("\n  All " + stepNo + " steps processed! Resume Preview is ready.");

        // ========================================================
        // CO3 - QUEUE : FluentRise Vocabulary Practice Queue
        // ========================================================
        section("CO3 - QUEUE : FluentRise English Vocab Practice Queue (FIFO)");
        Queue<String> vocabQueue = new LinkedList<>();
        String[] wordList = {
            "Eloquent   : Fluent and persuasive in speaking",
            "Articulate : Able to express thoughts clearly",
            "Tenacious  : Not giving up; very determined",
            "Diligent   : Careful and persistent hard work",
            "Proficient : Competent and skilled",
            "Versatile  : Able to adapt to many functions",
            "Innovative : Introducing new ideas or methods",
            "Pragmatic  : Dealing with things realistically",
            "Perseverance: Continued effort despite difficulty"
        };
        System.out.println("  Adding words to today's practice queue:");
        for (String w : wordList) {
            vocabQueue.offer(w);
            System.out.println("  ENQUEUE -> " + w);
        }
        System.out.println("\n  Student practising each word in order (FIFO):");
        int wc = 1;
        while (!vocabQueue.isEmpty())
            System.out.println("  PRACTICE [" + wc++ + "] -> " + vocabQueue.poll());
        System.out.println("\n  Practice session complete! All " + (wc - 1) + " words covered.");

        // ========================================================
        // CO4 - HASHMAP : Career Store (O(1) lookup)
        // ========================================================
        section("CO4 - HASHMAP : Career Store - O(1) Lookup");
        System.out.printf("  Total entries in careerStore HashMap: %d%n%n", careerStore.size());
        System.out.println("  Looking up your selected careers:");
        for (Career sel : selectedCareers) {
            Career found = careerStore.get(sel.name);
            if (found != null) {
                System.out.println("  GET[\"" + sel.name + "\"] -> FOUND:");
                found.printDetail();
            } else {
                System.out.println("  GET[\"" + sel.name + "\"] -> Key not found in HashMap.\n");
            }
        }

        // ========================================================
        // CO4 - HASHMAP : User Store (login data)
        // ========================================================
        section("CO4 - HASHMAP : User Store");
        System.out.println("  All " + userStore.size() + " entries in userStore:");
        for (Map.Entry<String, String> e : userStore.entrySet())
            System.out.printf("  KEY: %-15s  ->  VALUE: %s%n", e.getKey(), e.getValue());

        // ========================================================
        // CO4 - HASHMAP : Resume Store
        // ========================================================
        section("CO4 - HASHMAP : Resume Store");
        System.out.println("  All " + resumeStore.size() + " resume fields stored:");
        for (Map.Entry<String, String> e : resumeStore.entrySet())
            System.out.printf("  KEY: %-18s ->  VALUE: %s%n", e.getKey(), e.getValue());

        // ========================================================
        // CO4 - HASHMAP : Skill to Career Mapping
        // ========================================================
        section("CO4 - HASHMAP : Skill to Career Mapping");
        System.out.println("  Enter a skill -> instantly get matching careers (O(1) lookup):");
        String[] testSkills = {"Python", "Java", "Figma", "AWS", "SQL", "Kotlin"};
        for (String sk : testSkills) {
            List<String> careers = skillMap.get(sk);
            if (careers != null)
                System.out.printf("  Skill: %-12s -> Careers: %s%n", sk, careers);
            else
                System.out.printf("  Skill: %-12s -> No direct mapping found in HashMap.%n", sk);
        }

        // ========================================================
        // CO4 - HASHMAP : Dynamic Update (PUT / UPDATE / REMOVE)
        // ========================================================
        section("CO4 - HASHMAP : Dynamic Resume Update (PUT / UPDATE / REMOVE)");
        System.out.println("  [1] Original phone number  : " + resumeStore.get("phone"));
        System.out.print("  Enter new phone number     : ");
        String newPhone = sc.nextLine().trim();
        resumeStore.put("phone", newPhone);
        System.out.println("  [2] Updated  phone number  : " + resumeStore.get("phone"));

        System.out.print("  Enter your GitHub profile  : ");
        String github = sc.nextLine().trim();
        resumeStore.put("github", github);
        System.out.println("  [3] New key added - github : " + resumeStore.get("github"));

        System.out.print("  Enter your LinkedIn profile: ");
        String linkedin = sc.nextLine().trim();
        resumeStore.put("linkedin", linkedin);
        System.out.println("  [4] New key added - linkedin: " + resumeStore.get("linkedin"));

        resumeStore.remove("objective");
        System.out.println("  [5] Removed 'objective'. Key exists? " + resumeStore.containsKey("objective"));

        System.out.println("\n  Final resume store (" + resumeStore.size() + " fields):");
        for (Map.Entry<String, String> e : resumeStore.entrySet())
            System.out.printf("  %-18s ->  %s%n", e.getKey(), e.getValue());

        // ========================================================
        // GENERATED RESUME PRINTOUT
        // ========================================================
        section("GENERATED RESUME : Live Preview (Career Compass Resume Builder)");
        printResume();

        System.out.println();
        System.out.println("================================================================");
        System.out.println("  All COs demonstrated with detailed output!");
        System.out.println("  Career Compass - " + fullName);
        System.out.println("================================================================");

        sc.close();
    }

    // ============================================================
    // CO1 - LINEAR SEARCH on ArrayList
    // ============================================================
    static void linearSearchCareer(String query) {
        System.out.println("  Searching for: \"" + query + "\"");
        boolean found = false;
        for (int i = 0; i < careerList.size(); i++) {
            if (careerList.get(i).name.equalsIgnoreCase(query)) {
                System.out.println("    FOUND at index [" + i + "]");
                careerList.get(i).printDetail();
                found = true;
                break;
            }
        }
        if (!found) System.out.println("    \"" + query + "\" not found in list.\n");
        System.out.println();
    }

    // ============================================================
    // CO1 - BUBBLE SORT : salary descending (on selectedCareers)
    // ============================================================
    static void bubbleSortBySalary() {
        int n = selectedCareers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (selectedCareers.get(j).salary < selectedCareers.get(j + 1).salary) {
                    Career tmp = selectedCareers.get(j);
                    selectedCareers.set(j, selectedCareers.get(j + 1));
                    selectedCareers.set(j + 1, tmp);
                }
            }
        }
    }

    // ============================================================
    // CO1 - SELECTION SORT : demand descending (on selectedCareers)
    // ============================================================
    static void selectionSortByDemand() {
        int n = selectedCareers.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++)
                if (selectedCareers.get(j).demand > selectedCareers.get(maxIdx).demand)
                    maxIdx = j;
            if (maxIdx != i) {
                Career tmp = selectedCareers.get(i);
                selectedCareers.set(i, selectedCareers.get(maxIdx));
                selectedCareers.set(maxIdx, tmp);
            }
        }
    }

    // ============================================================
    // CO1 - Sort A to Z (prep for binary search)
    // ============================================================
    static void sortByNameAZ() {
        careerList.sort(Comparator.comparing(c -> c.name));
    }

    // ============================================================
    // CO1 - BINARY SEARCH : career name (list must be sorted A-Z)
    // ============================================================
    static void binarySearchCareer(String target) {
        System.out.println("  Binary search for: \"" + target + "\"");
        int lo = 0, hi = careerList.size() - 1, step = 0;
        boolean found = false;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            step++;
            String midName = careerList.get(mid).name;
            int cmp = target.compareToIgnoreCase(midName);
            if (cmp == 0) {
                System.out.println("    FOUND at index [" + mid + "] in " + step + " step(s).");
                careerList.get(mid).printDetail();
                found = true;
                break;
            } else if (cmp < 0) { hi = mid - 1; }
            else                 { lo = mid + 1; }
        }
        if (!found)
            System.out.println("    \"" + target + "\" not found after " + step + " step(s).\n");
        System.out.println();
    }

    // ============================================================
    // RESUME PRINT
    // ============================================================
    static void printResume() {
        System.out.println("  +-------------------------------------------------------+");
        System.out.println("  |                   CURRICULUM VITAE                    |");
        System.out.println("  +-------------------------------------------------------+");
        System.out.printf ("  |  Name       : %-41s|%n", resumeStore.getOrDefault("fullName", "N/A"));
        System.out.printf ("  |  Phone      : %-41s|%n", resumeStore.getOrDefault("phone", "N/A"));
        System.out.printf ("  |  Email      : %-41s|%n", resumeStore.getOrDefault("email", "N/A"));
        System.out.printf ("  |  GitHub     : %-41s|%n", resumeStore.getOrDefault("github", "N/A"));
        System.out.printf ("  |  LinkedIn   : %-41s|%n", resumeStore.getOrDefault("linkedin", "N/A"));
        System.out.println("  +-------------------------------------------------------+");
        System.out.printf ("  |  Education  : %-41s|%n", resumeStore.getOrDefault("education", "N/A"));
        System.out.printf ("  |  CGPA       : %-41s|%n", resumeStore.getOrDefault("cgpa", "N/A"));
        System.out.println("  +-------------------------------------------------------+");
        System.out.printf ("  |  Skills     : %-41s|%n", resumeStore.getOrDefault("skills", "N/A"));
        System.out.println("  +-------------------------------------------------------+");
        System.out.printf ("  |  Project    : %-41s|%n", resumeStore.getOrDefault("project", "N/A"));
        System.out.printf ("  |  Cert       : %-41s|%n", resumeStore.getOrDefault("cert", "N/A"));
        System.out.println("  +-------------------------------------------------------+");

        if (!selectedCareers.isEmpty()) {
            System.out.println("  +-------------------------------------------------------+");
            System.out.println("  |  Target Careers:                                      |");
            for (Career c : selectedCareers)
                System.out.printf("  |    -> %-49s|%n", c.name + " (" + c.salary + " LPA avg)");
        }

        System.out.println("  +-------------------------------------------------------+");
        System.out.println("  |            Resume generated by Career Compass          |");
        System.out.println("  +-------------------------------------------------------+");
    }

    // ============================================================
    // HELPERS
    // ============================================================
    static void banner(String title) {
        System.out.println("================================================================");
        System.out.println("  " + title);
        System.out.println("================================================================\n");
    }

    static void section(String title) {
        System.out.println("\n----------------------------------------------------------------");
        System.out.println("  " + title);
        System.out.println("----------------------------------------------------------------");
    }

    // ============================================================
    // CO2 - Load Careers into ArrayList
    // ============================================================
    static void loadCareers() {
        careerList.add(new Career("Software Engineer",         90, 85, 80, 18,
                new String[]{"Java", "Python", "DSA"}));
        careerList.add(new Career("Data Scientist",            88, 90, 78, 20,
                new String[]{"Python", "SQL", "ML"}));
        careerList.add(new Career("UI/UX Designer",            75, 80, 70, 12,
                new String[]{"Figma", "CSS", "Research"}));
        careerList.add(new Career("Cyber Security Analyst",    85, 88, 82, 16,
                new String[]{"Networking", "Linux", "Ethical Hacking"}));
        careerList.add(new Career("Machine Learning Engineer", 92, 95, 80, 22,
                new String[]{"Python", "TensorFlow", "Math"}));
        careerList.add(new Career("Cloud Architect",           87, 89, 83, 24,
                new String[]{"AWS", "Azure", "DevOps"}));
        careerList.add(new Career("Product Manager",           80, 82, 77, 19,
                new String[]{"Agile", "Communication", "Analytics"}));
        careerList.add(new Career("Full Stack Developer",      88, 86, 79, 17,
                new String[]{"React", "Node.js", "MongoDB"}));
        careerList.add(new Career("DevOps Engineer",           86, 87, 81, 18,
                new String[]{"Docker", "Kubernetes", "CI/CD"}));
        careerList.add(new Career("Mobile App Developer",      83, 84, 76, 15,
                new String[]{"Flutter", "Kotlin", "Swift"}));
    }

    // ============================================================
    // CO2 - Load Courses into ArrayList
    // ============================================================
    static void loadCourses() {
        courseList.add(new Course("Python for Beginners",             "Programming",     "Coursera",  6));
        courseList.add(new Course("Data Structures & Algorithms",     "CS Fundamentals", "Udemy",    10));
        courseList.add(new Course("Machine Learning A-Z",             "AI / ML",         "Coursera", 12));
        courseList.add(new Course("AWS Cloud Practitioner",           "Cloud",           "AWS",       8));
        courseList.add(new Course("Figma UI/UX Design Masterclass",   "Design",          "Udemy",     6));
        courseList.add(new Course("Ethical Hacking Complete Course",  "Cyber Security",  "Udemy",    14));
        courseList.add(new Course("React JS + Node.js Full Course",   "Web Dev",         "Udemy",    16));
        courseList.add(new Course("English Communication Skills",     "Soft Skills",     "Coursera",  4));
        courseList.add(new Course("Docker & Kubernetes Bootcamp",     "DevOps",          "Udemy",     8));
        courseList.add(new Course("Flutter Mobile Dev Bootcamp",      "Mobile Dev",      "Udemy",    10));
        courseList.add(new Course("SQL & Database Design",            "Database",        "edX",       5));
        courseList.add(new Course("Public Speaking & Confidence",     "Soft Skills",     "LinkedIn",  3));
    }

    // ============================================================
    // CO2 - Load Skills ArrayList
    // ============================================================
    static void loadSkills() {
        String[] s = {
            "Python","Java","JavaScript","HTML","CSS","React","Node.js",
            "SQL","MongoDB","AWS","Azure","Docker","Kubernetes","Figma",
            "Machine Learning","Deep Learning","Networking","Linux",
            "Flutter","Kotlin","Communication","Agile","Git","REST APIs"
        };
        for (String sk : s) skillPool.add(sk);
    }

    // ============================================================
    // CO2 - Load Vocabulary LinkedList (FluentRise English)
    // ============================================================
    static void loadVocab() {
        vocabList.add("Eloquent",     "Fluent and persuasive in speaking or writing");
        vocabList.add("Articulate",   "Able to speak fluently and coherently");
        vocabList.add("Tenacious",    "Not giving up; very determined");
        vocabList.add("Diligent",     "Careful and persistent hard work");
        vocabList.add("Proficient",   "Competent and skilled in doing something");
        vocabList.add("Versatile",    "Able to adapt to many functions or activities");
        vocabList.add("Innovative",   "Introducing new ideas; original and creative");
        vocabList.add("Pragmatic",    "Dealing with things sensibly and realistically");
        vocabList.add("Perseverance", "Continued effort despite difficulty or delay");
    }

    // ============================================================
    // CO4 - Load all HashMaps with user-provided data
    // ============================================================
    static void loadHashMaps(String fullName, String phone, String email,
                              String college, String branch, String year,
                              String cgpa, String skills, String project, String cert) {
        // Career store (O(1) lookup) - all careers
        for (Career c : careerList) careerStore.put(c.name, c);

        // User store
        userStore.put("username",  fullName);
        userStore.put("email",     email);
        userStore.put("college",   college);
        userStore.put("branch",    branch);
        userStore.put("year",      year);
        userStore.put("role",      "Student");
        userStore.put("theme",     "dark");
        userStore.put("language",  "English");

        // Resume store
        resumeStore.put("fullName",  fullName);
        resumeStore.put("phone",     phone);
        resumeStore.put("email",     email);
        resumeStore.put("education", branch + " - " + college);
        resumeStore.put("cgpa",      cgpa);
        resumeStore.put("skills",    skills);
        resumeStore.put("project",   project);
        resumeStore.put("cert",      cert);
        resumeStore.put("objective", "To build a career in my chosen field");

        // Skill to Career mapping
        skillMap.put("Python", Arrays.asList("Data Scientist", "ML Engineer", "Software Engineer"));
        skillMap.put("Java",   Arrays.asList("Software Engineer", "Android Developer", "Backend Dev"));
        skillMap.put("Figma",  Arrays.asList("UI/UX Designer", "Product Manager"));
        skillMap.put("AWS",    Arrays.asList("Cloud Architect", "DevOps Engineer", "Solutions Architect"));
        skillMap.put("SQL",    Arrays.asList("Data Scientist", "Database Admin", "Backend Dev"));
    }
}