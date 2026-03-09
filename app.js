const tabButtons = document.querySelectorAll(".tab-btn");
const authForms = document.querySelectorAll(".auth-form");
const authScreen = document.getElementById("authScreen");
const portalScreen = document.getElementById("portalScreen");
const registerForm = document.getElementById("registerForm");
const loginForm = document.getElementById("loginForm");
const logoutBtn = document.getElementById("logoutBtn");
const mobileMenuBtn = document.getElementById("mobileMenuBtn");
const sidebar = document.getElementById("sidebar");
const welcomeTitle = document.getElementById("welcomeTitle");

const navLinks = document.querySelectorAll(".nav-link");
const pages = document.querySelectorAll(".page");
const quickNavButtons = document.querySelectorAll(".quick-nav");

const motivations = [
  "Small daily progress builds big dreams. Believe in your effort.",
  "You do not need to be perfect. You need to keep moving.",
  "Your background does not decide your future. Your actions do.",
  "Fear becomes smaller when practice becomes stronger.",
  "Success starts when you trust yourself one more time."
];

const courses = [
  { title: "CSE", text: "Computer Science and Engineering focuses on coding, software, AI, data, cybersecurity, and digital problem solving." },
  { title: "ECE", text: "Electronics and Communication Engineering deals with circuits, communication systems, embedded systems, and hardware technologies." },
  { title: "Mechanical", text: "Mechanical Engineering covers machines, manufacturing, thermal systems, automobiles, and industrial design." },
  { title: "Civil", text: "Civil Engineering is about construction, structures, roads, water systems, and urban development." },
  { title: "EEE", text: "Electrical and Electronics Engineering focuses on power systems, electrical machines, control systems, and electronics." },
  { title: "Biotech", text: "Biotechnology blends biology and technology for health, agriculture, food, and laboratory innovation." },
  { title: "Commerce", text: "Commerce builds knowledge in accounting, finance, taxation, business, economics, and management." },
  { title: "Arts & Humanities", text: "Arts develops communication, writing, public service, social science, psychology, teaching, and creative thinking." },
  { title: "Polytechnic / Diploma", text: "Diploma courses focus on practical technical learning and can lead to jobs or lateral entry into engineering." }
];

const careers = {
  "Software Engineer": {
    category: "Engineering & Technical",
    level: "Excellent",
    description: "Designs and builds software, apps, websites, and systems.",
    demand: 90, growth: 88, stability: 78, salary: 85,
    roadmap: [
      "Study MPC or diploma with maths foundation.",
      "Learn programming languages like Python, JavaScript, or Java.",
      "Build projects and portfolio.",
      "Complete internships and teamwork experience.",
      "Apply for software developer roles or startups."
    ],
    skills: ["Programming", "Problem Solving", "Data Structures", "Web Development", "Teamwork", "Logical Thinking"]
  },
  "Doctor": {
    category: "Healthcare Support Services",
    level: "Excellent",
    description: "Diagnoses illnesses and treats patients using medical knowledge.",
    demand: 92, growth: 86, stability: 95, salary: 84,
    roadmap: [
      "Choose BiPC stream.",
      "Prepare for medical entrance exams.",
      "Complete MBBS and internship.",
      "Optionally specialize.",
      "Practice medicine with ethics and compassion."
    ],
    skills: ["Biology Knowledge", "Empathy", "Diagnosis", "Attention to Detail", "Communication", "Discipline"]
  },
  "Teacher": {
    category: "Education Training",
    level: "Very Good",
    description: "Educates students and develops knowledge and character.",
    demand: 75, growth: 72, stability: 82, salary: 62,
    roadmap: [
      "Choose a subject specialization.",
      "Complete graduation degree.",
      "Do B.Ed or teacher training.",
      "Develop teaching skills and communication.",
      "Work in schools or coaching institutes."
    ],
    skills: ["Subject Knowledge", "Public Speaking", "Patience", "Explanation Skills", "Planning", "Communication"]
  },
  "Pilot": {
    category: "Aviation",
    level: "Excellent",
    description: "Operates aircraft and ensures safe flight operations.",
    demand: 70, growth: 75, stability: 74, salary: 88,
    roadmap: [
      "Take MPC with physics and maths.",
      "Pass medical fitness tests.",
      "Join flying school.",
      "Gain flight hours and licenses.",
      "Apply to airline companies."
    ],
    skills: ["Decision Making", "Discipline", "Technical Awareness", "Calmness", "Responsibility", "English Communication"]
  },
  "Artist": {
    category: "Animation, Graphics & Design",
    level: "Good",
    description: "Creates visual or performance art across media and design.",
    demand: 64, growth: 70, stability: 54, salary: 58,
    roadmap: [
      "Choose art specialization.",
      "Practice drawing or design daily.",
      "Learn digital tools.",
      "Build portfolio.",
      "Showcase work online or exhibitions."
    ],
    skills: ["Creativity", "Design Sense", "Practice Discipline", "Visual Thinking", "Presentation"]
  },
  "Scientist": {
    category: "Science & Mathematics",
    level: "Excellent",
    description: "Conducts research and experiments to discover new knowledge.",
    demand: 70, growth: 77, stability: 80, salary: 76,
    roadmap: [
      "Build strong maths and science foundation.",
      "Complete bachelor and master degree.",
      "Join research labs.",
      "Publish studies and innovations.",
      "Work in universities or labs."
    ],
    skills: ["Research", "Analysis", "Scientific Curiosity", "Maths", "Lab Skills", "Patience"]
  },
  "Chef": {
    category: "Hospitality & Tourism Services",
    level: "Good",
    description: "Creates professional food dishes in hotels or restaurants.",
    demand: 67, growth: 69, stability: 64, salary: 60,
    roadmap: [
      "Develop cooking passion.",
      "Study hotel management or culinary arts.",
      "Practice recipes and plating.",
      "Gain kitchen experience.",
      "Become head chef or start restaurant."
    ],
    skills: ["Cooking Techniques", "Creativity", "Hygiene", "Time Management", "Presentation"]
  },
  "Banking Professional": {
    category: "Banking Financial Services & Insurance",
    level: "Very Good",
    description: "Works in banking, finance, and financial services.",
    demand: 78, growth: 73, stability: 84, salary: 72,
    roadmap: [
      "Study commerce or finance.",
      "Learn accounting and economics.",
      "Prepare for banking exams.",
      "Improve customer service skills.",
      "Grow into officer or manager roles."
    ],
    skills: ["Numerical Ability", "Finance Knowledge", "Excel", "Customer Service", "Communication"]
  },
  "Entrepreneur": {
    category: "Business Operations & Entrepreneurship",
    level: "High Potential",
    description: "Creates businesses and solves market problems.",
    demand: 75, growth: 90, stability: 50, salary: 82,
    roadmap: [
      "Identify a market problem.",
      "Learn business basics.",
      "Start small project or startup.",
      "Build brand and marketing.",
      "Scale business gradually."
    ],
    skills: ["Leadership", "Business Planning", "Sales", "Risk Taking", "Adaptability"]
  },
  "Agriculture Specialist": {
    category: "Agriculture & Food",
    level: "Good",
    description: "Works with farming science, food production, and agri-technology.",
    demand: 68, growth: 70, stability: 73, salary: 60,
    roadmap: [
      "Study agriculture science.",
      "Learn farming technologies.",
      "Practice crop management.",
      "Work in agri companies or farms.",
      "Promote sustainable farming."
    ],
    skills: ["Plant Science", "Field Work", "Problem Solving", "Sustainability", "Planning"]
  },
  "Media Creator": {
    category: "Media & Entertainment",
    level: "Good",
    description: "Creates digital media content, videos, and communication projects.",
    demand: 72, growth: 78, stability: 60, salary: 65,
    roadmap: [
      "Learn media tools and editing.",
      "Practice storytelling and communication.",
      "Build online content portfolio.",
      "Collaborate with brands or studios.",
      "Grow audience and influence."
    ],
    skills: ["Creativity", "Video Editing", "Storytelling", "Communication", "Marketing"]
  },
  "Sports Professional": {
    category: "Sports & Fitness",
    level: "Good",
    description: "Builds career in athletics, coaching, or fitness training.",
    demand: 60, growth: 68, stability: 55, salary: 58,
    roadmap: [
      "Train in specific sport.",
      "Maintain physical fitness.",
      "Participate in competitions.",
      "Get coaching certification.",
      "Become coach or athlete."
    ],
    skills: ["Fitness", "Discipline", "Teamwork", "Practice", "Mental Strength"]
  },
  "Construction Engineer": {
    category: "Construction",
    level: "Very Good",
    description: "Designs and manages construction projects and infrastructure.",
    demand: 75, growth: 72, stability: 80, salary: 70,
    roadmap: [
      "Study civil engineering.",
      "Learn project management.",
      "Work on site training.",
      "Gain structural design skills.",
      "Lead infrastructure projects."
    ],
    skills: ["Engineering Knowledge", "Planning", "Site Management", "Safety Awareness", "Teamwork"]
  },
  "Electronics Engineer": {
    category: "Electronics & Hardware",
    level: "Very Good",
    description: "Develops electronic devices, circuits, and hardware systems.",
    demand: 74, growth: 76, stability: 78, salary: 72,
    roadmap: [
      "Study ECE or electronics.",
      "Learn circuit design.",
      "Practice embedded systems.",
      "Work in hardware industries.",
      "Innovate electronic products."
    ],
    skills: ["Circuit Design", "Hardware Knowledge", "Problem Solving", "Testing", "Technical Skills"]
  }
};

const vocabularyWords = [
  { word: "Ambition", meaning: "A strong desire to achieve something great." },
  { word: "Confidence", meaning: "Belief in your ability and value." },
  { word: "Discipline", meaning: "Regular control and effort toward goals." },
  { word: "Opportunity", meaning: "A good chance for progress or success." },
  { word: "Resilient", meaning: "Able to recover and continue after difficulties." },
  { word: "Communicate", meaning: "To share ideas clearly with others." }
];

const dictionaryWords = [
  { word: "Career", meaning: "A long-term journey of work and growth." },
  { word: "Roadmap", meaning: "A step-by-step plan to reach a goal." },
  { word: "Skill", meaning: "An ability learned through practice and experience." },
  { word: "Motivation", meaning: "The inner reason to start and continue." },
  { word: "Stress", meaning: "Mental or emotional pressure from challenges." },
  { word: "Interview", meaning: "A formal conversation for selection or evaluation." }
];

const confidenceTopics = [
  { title: "Stage Fear", text: "Your voice matters. Start with small speaking practice, breathe deeply, and improve step by step." },
  { title: "Maths Fear", text: "Maths becomes easier when broken into steps. Practice basics daily and celebrate progress." },
  { title: "Subject Fear", text: "No subject is impossible. The right method and revision can change your results." },
  { title: "Exam Tension", text: "Exams test preparation, not your worth. Revise calmly and trust your effort." },
  { title: "English Speaking Fear", text: "Mistakes are part of learning. Speak simple English first and improve slowly." },
  { title: "Stress & Overthinking", text: "Pause, breathe, write your thoughts, and focus on one small action." }
];

const boostMessages = [
  "You are not weak because you feel afraid. You are brave because you still continue.",
  "Confidence grows when you practice, not when you wait.",
  "Every expert was once a beginner who felt nervous too.",
  "Your future does not need perfect steps. It needs your next step.",
  "You are capable of more than your fear is telling you."
];

function switchAuthTab(tab) {
  tabButtons.forEach(btn => btn.classList.toggle("active", btn.dataset.authTab === tab));
  authForms.forEach(form => form.classList.toggle("active", form.id === `${tab}Form`));
}

tabButtons.forEach(btn => {
  btn.addEventListener("click", () => switchAuthTab(btn.dataset.authTab));
});

registerForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const name = document.getElementById("registerName").value.trim();
  const username = document.getElementById("registerUsername").value.trim();
  const password = document.getElementById("registerPassword").value.trim();

  if (!name || !username || !password) {
    alert("Please fill all register fields.");
    return;
  }

  localStorage.setItem("aspireblueUser", JSON.stringify({ name, username, password }));
  alert("Registered successfully. Now login with your username and password.");
  switchAuthTab("login");
  loginForm.reset();
});

loginForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const username = document.getElementById("loginUsername").value.trim();
  const password = document.getElementById("loginPassword").value.trim();
  const user = JSON.parse(localStorage.getItem("aspireblueUser") || "null");

  if (!user) {
    alert("No user found. Please register first.");
    switchAuthTab("register");
    return;
  }

  if (user.username === username && user.password === password) {
    localStorage.setItem("aspireblueLoggedIn", "true");
    showPortal(user.name);
  } else {
    alert("Invalid username or password.");
  }
});

function showPortal(name) {
  authScreen.classList.add("hidden");
  portalScreen.classList.remove("hidden");
  welcomeTitle.textContent = `Hello ${name || "Student"} 👋`;
}

function showAuth() {
  authScreen.classList.remove("hidden");
  portalScreen.classList.add("hidden");
}

logoutBtn.addEventListener("click", () => {
  localStorage.removeItem("aspireblueLoggedIn");
  showAuth();
});

mobileMenuBtn.addEventListener("click", () => {
  sidebar.classList.toggle("open");
});

function switchPage(pageId) {
  pages.forEach(page => page.classList.toggle("active", page.id === pageId));
  navLinks.forEach(link => link.classList.toggle("active", link.dataset.target === pageId));
  sidebar.classList.remove("open");
}

navLinks.forEach(link => {
  link.addEventListener("click", () => switchPage(link.dataset.target));
});

quickNavButtons.forEach(btn => {
  btn.addEventListener("click", () => switchPage(btn.dataset.go));
});

document.getElementById("newMotivationBtn").addEventListener("click", () => {
  const quote = motivations[Math.floor(Math.random() * motivations.length)];
  document.getElementById("motivationText").textContent = quote;
});

function createCard(title, text) {
  return `
    <article class="glass-card">
      <h3>${title}</h3>
      <p>${text}</p>
    </article>
  `;
}

document.getElementById("coursesGrid").innerHTML = courses
  .map(course => createCard(course.title, course.text))
  .join("");

const careerChipList = document.getElementById("careerChipList");
careerChipList.innerHTML = Object.keys(careers)
  .map(name => `<button class="career-chip ${name === "Software Engineer" ? "active" : ""}" data-career="${name}">${name}</button>`)
  .join("");

let currentCareer = "Software Engineer";

function updateCareerView(name) {
  const fallback = {
    category: "Custom Career",
    level: "Average",
    description: `${name} is a valuable career path. Learn the basics, build skills, seek mentors, practice regularly, and create a strong roadmap.`,
    demand: 65, growth: 66, stability: 63, salary: 60,
    roadmap: [
      "Understand what the career does in the real world.",
      "Choose the right course, degree, diploma, or training.",
      "Build subject knowledge and practical skills.",
      "Practice communication, confidence, and portfolio development.",
      "Seek internships, mentors, and real experience."
    ],
    skills: ["Communication", "Discipline", "Learning Ability", "Problem Solving", "Confidence"]
  };

  const career = careers[name] || fallback;

  document.getElementById("careerTitle").textContent = name;
  document.getElementById("careerCategory").textContent = career.category;
  document.getElementById("careerLevel").textContent = career.level;
  document.getElementById("careerDescription").textContent = career.description;
  document.getElementById("demandBar").style.width = `${career.demand}%`;
  document.getElementById("growthBar").style.width = `${career.growth}%`;
  document.getElementById("stabilityBar").style.width = `${career.stability}%`;
  document.getElementById("salaryBar").style.width = `${career.salary}%`;
  document.getElementById("careerRoadmap").innerHTML = career.roadmap.map(step => `<li>${step}</li>`).join("");

  currentCareer = name;
  updateSkillsSection(name);

  document.querySelectorAll(".career-chip").forEach(chip => {
    chip.classList.toggle("active", chip.dataset.career === name);
  });
}

careerChipList.addEventListener("click", (e) => {
  if (e.target.classList.contains("career-chip")) {
    updateCareerView(e.target.dataset.career);
  }
});

document.getElementById("searchCareerBtn").addEventListener("click", () => {
  const careerName = document.getElementById("careerSearch").value.trim();
  if (!careerName) return;
  updateCareerView(careerName);
});

function getRecommendationFromQuiz() {
  const stream = document.getElementById("q1").value;
  const interest = document.getElementById("q3").value;
  const subject = document.getElementById("q17").value;
  const dream = document.getElementById("q21").value.trim();

  if (dream && careers[dream]) return dream;
  if (interest === "Technology" || subject === "Maths" || stream === "MPC") return "Software Engineer";
  if (interest === "Medicine / Healthcare" || subject === "Biology" || stream === "BiPC") return "Doctor";
  if (interest === "Teaching / Social Science") return "Teacher";
  if (interest === "Business / Entrepreneurship" || stream === "Commerce") return "Entrepreneur";
  return "Software Engineer";
}

document.getElementById("quizForm").addEventListener("submit", (e) => {
  e.preventDefault();

  const recommendation = getRecommendationFromQuiz();
  const strengths = document.getElementById("q9").value.trim() || "You have potential that can grow with regular effort.";
  const weaknesses = document.getElementById("q10").value.trim() || "Keep observing areas to improve without self-doubt.";
  const support = document.getElementById("q22").value;
  const profileText = document.getElementById("q8").value.trim() || "You are a student with developing interests and future goals.";

  const resultText = `
Profile Summary:
${profileText}

Recommended Career:
${recommendation}

Strengths Observed:
${strengths}

Improvement Areas:
${weaknesses}

Recommendations:
• Focus on ${recommendation} as a strong matching direction.
• Build communication, confidence, and practical experience.
• Use the Dream section to read roadmap and future graph.
• Use the Skills section to develop required abilities.
• Priority Support Needed: ${support}.
  `;

  document.getElementById("quizResult").textContent = resultText;
  updateCareerView(recommendation);
  switchPage("dreams");
  alert(`Your recommended career is ${recommendation}. Check the Dream section for roadmap and graph.`);
});

function updateSkillsSection(name) {
  const skillCareer = careers[name] || {
    skills: ["Communication", "Problem Solving", "Learning Ability", "Teamwork", "Confidence"]
  };

  document.getElementById("skillsCareerTitle").textContent = `Skills for ${name}`;
  document.getElementById("skillsList").innerHTML = skillCareer.skills
    .map(skill => `<li>${skill}</li>`)
    .join("");
}

document.getElementById("syncSkillsBtn").addEventListener("click", () => {
  updateSkillsSection(currentCareer);
  switchPage("skills");
});

function renderVocabulary() {
  document.getElementById("vocabGrid").innerHTML = vocabularyWords
    .map(item => `<div class="vocab-item"><strong>${item.word}</strong><span>${item.meaning}</span></div>`)
    .join("");

  document.getElementById("dictionaryList").innerHTML = dictionaryWords
    .map(item => `<div class="dict-item"><strong>${item.word}</strong><span>${item.meaning}</span></div>`)
    .join("");
}

function renderConfidence() {
  document.getElementById("confidenceGrid").innerHTML = confidenceTopics
    .map(item => `<article class="glass-card confidence-item"><strong>${item.title}</strong><span>${item.text}</span></article>`)
    .join("");
}

document.getElementById("confidenceBtn").addEventListener("click", () => {
  const msg = boostMessages[Math.floor(Math.random() * boostMessages.length)];
  document.getElementById("confidenceMessage").textContent = msg;
});

// Resume Builder
const fields = {
  templateSelect: document.getElementById("templateSelect"),
  fullName: document.getElementById("fullName"),
  role: document.getElementById("role"),
  email: document.getElementById("email"),
  phone: document.getElementById("phone"),
  location: document.getElementById("location"),
  links: document.getElementById("links"),
  summary: document.getElementById("summary"),
  education: document.getElementById("education"),
  projects: document.getElementById("projects"),
  experience: document.getElementById("experience"),
  skills: document.getElementById("skills"),
  certs: document.getElementById("certs")
};

const preview = {
  resume: document.getElementById("resumePreview"),
  pName: document.getElementById("pName"),
  pRole: document.getElementById("pRole"),
  pEmail: document.getElementById("pEmail"),
  pPhone: document.getElementById("pPhone"),
  pLocation: document.getElementById("pLocation"),
  pLinks: document.getElementById("pLinks"),
  pSummary: document.getElementById("pSummary"),
  pEducation: document.getElementById("pEducation"),
  pProjects: document.getElementById("pProjects"),
  pExperience: document.getElementById("pExperience"),
  pSkills: document.getElementById("pSkills"),
  pCerts: document.getElementById("pCerts")
};

function escapeHtml(str) {
  return (str || "").replace(/[&<>"']/g, (m) => ({
    "&": "&amp;",
    "<": "&lt;",
    ">": "&gt;",
    '"': "&quot;",
    "'": "&#039;"
  }[m]));
}

function linesToDivs(text) {
  const lines = (text || "").split("\n").map(l => l.trim()).filter(Boolean);
  if (!lines.length) return "";
  return lines.map(l => `<div>${escapeHtml(l)}</div>`).join("");
}

function blockToHtml(text) {
  const raw = (text || "").trim();
  if (!raw) return "";
  const blocks = raw.split(/\n\s*\n/g);

  return blocks.map(block => {
    const lines = block.split("\n").map(l => l.trim()).filter(Boolean);
    if (!lines.length) return "";

    const header = lines[0];
    const rest = lines.slice(1);
    const bullets = rest.filter(l => l.startsWith("•") || l.startsWith("-"));
    const others = rest.filter(l => !(l.startsWith("•") || l.startsWith("-")));

    let out = `<div style="margin-bottom:10px;"><div style="font-weight:700;font-size:13px;">${escapeHtml(header)}</div>`;
    if (others.length) {
      out += `<div style="font-size:12px;color:#4b5563;margin-top:2px;">${escapeHtml(others.join(" • "))}</div>`;
    }
    if (bullets.length) {
      out += `<ul>` + bullets.map(b => `<li>${escapeHtml(b.replace(/^([•-]\s*)/, ""))}</li>`).join("") + `</ul>`;
    }
    out += `</div>`;
    return out;
  }).join("");
}

function skillsToChips(text) {
  const items = (text || "").split(",").map(s => s.trim()).filter(Boolean);
  if (!items.length) return `<div class="muted-preview">Add skills (comma separated).</div>`;
  return items.map(item => `<span class="resume-chip">${escapeHtml(item)}</span>`).join("");
}

function updatePreview() {
  preview.pName.textContent = fields.fullName.value.trim() || "Your Name";
  preview.pRole.textContent = fields.role.value.trim() || "Target Role";
  preview.pEmail.textContent = fields.email.value.trim() || "email@example.com";
  preview.pPhone.textContent = fields.phone.value.trim() || "+91 00000 00000";
  preview.pLocation.textContent = fields.location.value.trim() || "City, Country";
  preview.pLinks.textContent = fields.links.value.trim() || "linkedin.com/in/...";
  preview.pSummary.textContent = fields.summary.value.trim() || "Write a short objective/summary.";

  preview.pEducation.innerHTML = linesToDivs(fields.education.value) || `<div class="muted-preview">Add your education details.</div>`;
  preview.pProjects.innerHTML = blockToHtml(fields.projects.value) || `<div class="muted-preview">Add your projects with bullet points.</div>`;
  preview.pExperience.innerHTML = blockToHtml(fields.experience.value) || `<div class="muted-preview">Add internships/experience if any.</div>`;
  preview.pSkills.innerHTML = skillsToChips(fields.skills.value);
  preview.pCerts.innerHTML = linesToDivs(fields.certs.value) || `<div class="muted-preview">Add certifications (optional).</div>`;

  preview.resume.classList.remove("modern", "classic");
  preview.resume.classList.add(fields.templateSelect.value);
}

function getResumeData() {
  return {
    template: fields.templateSelect.value,
    fullName: fields.fullName.value,
    role: fields.role.value,
    email: fields.email.value,
    phone: fields.phone.value,
    location: fields.location.value,
    links: fields.links.value,
    summary: fields.summary.value,
    education: fields.education.value,
    projects: fields.projects.value,
    experience: fields.experience.value,
    skills: fields.skills.value,
    certs: fields.certs.value
  };
}

function setResumeData(data) {
  if (!data) return;
  fields.templateSelect.value = data.template || "modern";
  fields.fullName.value = data.fullName || "";
  fields.role.value = data.role || "";
  fields.email.value = data.email || "";
  fields.phone.value = data.phone || "";
  fields.location.value = data.location || "";
  fields.links.value = data.links || "";
  fields.summary.value = data.summary || "";
  fields.education.value = data.education || "";
  fields.projects.value = data.projects || "";
  fields.experience.value = data.experience || "";
  fields.skills.value = data.skills || "";
  fields.certs.value = data.certs || "";
  updatePreview();
}

Object.values(fields).forEach(field => {
  field.addEventListener("input", updatePreview);
  field.addEventListener("change", updatePreview);
});

document.getElementById("btnPrint").addEventListener("click", () => window.print());

document.getElementById("btnSave").addEventListener("click", () => {
  localStorage.setItem("aspireblue_resume", JSON.stringify(getResumeData()));
  alert("Resume saved successfully.");
});

document.getElementById("btnLoad").addEventListener("click", () => {
  const data = JSON.parse(localStorage.getItem("aspireblue_resume") || "null");
  if (!data) return alert("No saved resume found.");
  setResumeData(data);
  alert("Resume loaded successfully.");
});

document.getElementById("btnClear").addEventListener("click", () => {
  if (!confirm("Clear all resume fields?")) return;
  setResumeData({ template: "modern" });
  localStorage.removeItem("aspireblue_resume");
});

function init() {
  renderVocabulary();
  renderConfidence();
  updateCareerView("Software Engineer");
  updateSkillsSection("Software Engineer");
  updatePreview();

  const savedUser = JSON.parse(localStorage.getItem("aspireblueUser") || "null");
  const isLoggedIn = localStorage.getItem("aspireblueLoggedIn") === "true";
  if (savedUser && isLoggedIn) {
    showPortal(savedUser.name);
  }
}

init();