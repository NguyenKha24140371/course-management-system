/* =========================================================
   API CLIENT
   Talks to the CMS Spring Boot backend (/api/**).
   If the backend isn't running (e.g. static preview), every
   call transparently falls back to DEMO data so the UI never
   looks broken.
   ========================================================= */

const API_BASE = '/api';
const TOKEN_KEY = 'cms_token';
const USER_KEY  = 'cms_user';

const Auth = {
  getToken(){ return localStorage.getItem(TOKEN_KEY); },
  setSession(token, user){
    localStorage.setItem(TOKEN_KEY, token);
    localStorage.setItem(USER_KEY, JSON.stringify(user || {}));
  },
  getUser(){
    try{ return JSON.parse(localStorage.getItem(USER_KEY) || 'null'); }catch(e){ return null; }
  },
  isLoggedIn(){ return !!Auth.getToken(); },
  logout(){ localStorage.removeItem(TOKEN_KEY); localStorage.removeItem(USER_KEY); }
};

async function apiFetch(path, options = {}){
  const headers = Object.assign({ 'Content-Type': 'application/json' }, options.headers || {});
  const token = Auth.getToken();
  if (token) headers['Authorization'] = `Bearer ${token}`;
  const res = await fetch(API_BASE + path, { ...options, headers });
  if (!res.ok){
    let msg = `Request failed (${res.status})`;
    try{ const body = await res.json(); msg = body.message || msg; }catch(e){}
    throw new Error(msg);
  }
  const text = await res.text();
  return text ? JSON.parse(text) : null;
}

/* ---------- DEMO DATA (used only as an offline fallback) ---------- */
const DEMO_COURSES = [
  { id:1, title:'Business Development Fundamentals', description:'Learn how to grow a business from zero to a sustainable revenue stream, told through real case studies.', price:23, duration:6, instructorName:'Akila M.', rating:4, badge:'promo', img:'https://images.unsplash.com/photo-1552664730-d307ca884978?q=80&w=400&auto=format&fit=crop' },
  { id:2, title:'Floristics: Design & Arrangement', description:'A hands-on introduction to flower arranging, color theory, and seasonal composition.', price:23, duration:4, instructorName:'Akila M.', rating:4, badge:null, img:'https://images.unsplash.com/photo-1490750967868-88aa4486c946?q=80&w=400&auto=format&fit=crop' },
  { id:3, title:'Famous Composers', description:'A tour through the lives and works of the composers who shaped Western music.', price:23, duration:8, instructorName:'Akila M.', rating:4, badge:'new', img:'https://images.unsplash.com/photo-1465225314224-587cd83d322b?q=80&w=400&auto=format&fit=crop' },
  { id:4, title:'Communication that Lands', description:'Practical frameworks for clearer writing, better meetings, and difficult conversations.', price:23, duration:5, instructorName:'Akila M.', rating:4, badge:null, img:'https://images.unsplash.com/photo-1521791136064-7986c2920216?q=80&w=400&auto=format&fit=crop' },
  { id:5, title:'UX/UI Design Foundations', description:'From wireframe to prototype: the core process behind interfaces people enjoy using.', price:23, duration:10, instructorName:'Akila M.', rating:4, badge:'hot', img:'https://images.unsplash.com/photo-1587440871875-191322ee64b0?q=80&w=400&auto=format&fit=crop' },
  { id:6, title:'Intro to Game Theory', description:'Poker, chess and everyday decisions — the shared logic of strategic thinking.', price:19, duration:6, instructorName:'D. Marlowe', rating:5, badge:null, img:'https://images.unsplash.com/photo-1611996575749-79a3a250f948?q=80&w=400&auto=format&fit=crop' },
];

const DEMO_CATEGORIES = [
  { name:'Certifications', items:['HACCP','First Aid','Other certifications'] },
  { name:'Education', items:['Math','Physics','Biology','Chemistry','Geography','Literature'], more:true },
  { name:'Exams', items:['All exams'] },
  { name:'Game theory', items:['Poker','Chess','Blackjack','Mahjong'] },
  { name:'Health', items:['Aerobic exercise','Anaerobic exercise','High intensity interval training','Other health'] },
  { name:'Hospitality', items:['Food','Beverage','Operations','Other hospitality'] },
  { name:'Languages', items:['English','Spanish','French','German'] },
  { name:'Other', items:['Other'] },
];

/* ---------- Courses ---------- */
const CourseAPI = {
  async list({ page = 0, size = 20, sort } = {}){
    try{
      const qs = new URLSearchParams({ page, size, ...(sort ? { sort } : {}) });
      const data = await apiFetch(`/courses?${qs.toString()}`);
      return (data && data.content) ? data.content : (Array.isArray(data) ? data : DEMO_COURSES);
    }catch(e){
      return DEMO_COURSES;
    }
  },
  async search(keyword){
    try{
      const qs = new URLSearchParams({ keyword });
      const data = await apiFetch(`/courses/search?${qs.toString()}`);
      return (data && data.content) ? data.content : DEMO_COURSES;
    }catch(e){
      const k = keyword.toLowerCase();
      return DEMO_COURSES.filter(c => c.title.toLowerCase().includes(k));
    }
  },
  async get(id){
    try{ return await apiFetch(`/courses/${id}`); }
    catch(e){ return DEMO_COURSES.find(c => String(c.id) === String(id)) || DEMO_COURSES[0]; }
  }
};

const LessonAPI = {
  async listByCourse(courseId){
    try{ return await apiFetch(`/lessons/course/${courseId}`); }
    catch(e){
      return [
        { id:1, title:'Welcome & course overview', duration:8 },
        { id:2, title:'Core concepts', duration:22 },
        { id:3, title:'Hands-on walkthrough', duration:31 },
        { id:4, title:'Wrap-up & next steps', duration:12 },
      ];
    }
  }
};

const EnrollmentAPI = {
  async enroll(courseId, studentId){
    return apiFetch('/enrollments', {
      method: 'POST',
      body: JSON.stringify({ courseId, studentId })
    });
  }
};

const AuthAPI = {
  async login(username, password){
    return apiFetch('/auth/login', { method:'POST', body: JSON.stringify({ username, password }) });
  },
  async register(payload){
    return apiFetch('/auth/register', { method:'POST', body: JSON.stringify(payload) });
  }
};

function money(v){ return '€' + Number(v).toFixed(0); }

function starsHTML(rating = 4, max = 5){
  let out = '';
  for (let i = 1; i <= max; i++){
    out += `<span class="${i <= rating ? '' : 'dim'}">★</span>`;
  }
  return `<span class="stars">${out}</span>`;
}

function toast(message, type = 'success'){
  let stack = document.querySelector('.toast-stack');
  if (!stack){
    stack = document.createElement('div');
    stack.className = 'toast-stack';
    document.body.appendChild(stack);
  }
  const el = document.createElement('div');
  el.className = `toast ${type === 'error' ? 'error' : ''}`;
  el.textContent = message;
  stack.appendChild(el);
  setTimeout(() => el.remove(), 3200);
}
