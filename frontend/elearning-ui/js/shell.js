/* =========================================================
   APP SHELL RENDERER
   Injects sidebar + topbar markup so every page shares one
   source of truth for navigation.
   ========================================================= */

const ICONS = {
  dashboard: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><rect x="3" y="3" width="8" height="8" rx="2"/><rect x="13" y="3" width="8" height="5" rx="2"/><rect x="13" y="12" width="8" height="9" rx="2"/><rect x="3" y="14" width="8" height="7" rx="2"/></svg>',
  create: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><path d="M12 5v14M5 12h14" stroke-linecap="round"/></svg>',
  courses: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><path d="M4 5.5A2.5 2.5 0 0 1 6.5 3H20v15H6.5A2.5 2.5 0 0 0 4 20.5v-15Z"/><path d="M4 20.5A2.5 2.5 0 0 1 6.5 18H20"/></svg>',
  platform: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><rect x="3" y="4" width="18" height="13" rx="2"/><path d="M8 21h8M12 17v4" stroke-linecap="round"/></svg>',
  admin: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><circle cx="12" cy="12" r="3"/><path d="M19.4 15a1.7 1.7 0 0 0 .34 1.87l.06.06a2 2 0 1 1-2.83 2.83l-.06-.06a1.7 1.7 0 0 0-1.87-.34 1.7 1.7 0 0 0-1.04 1.56V21a2 2 0 1 1-4 0v-.09A1.7 1.7 0 0 0 8.96 19a1.7 1.7 0 0 0-1.87.34l-.06.06a2 2 0 1 1-2.83-2.83l.06-.06A1.7 1.7 0 0 0 4.6 15 1.7 1.7 0 0 0 3 14H2.91a2 2 0 1 1 0-4H3a1.7 1.7 0 0 0 1.6-1.04 1.7 1.7 0 0 0-.34-1.87l-.06-.06a2 2 0 1 1 2.83-2.83l.06.06A1.7 1.7 0 0 0 9 4.6c.51-.19 1-.6 1.04-1.6H10a2 2 0 1 1 4 0v.09c.04 1 .53 1.41 1.04 1.6.6.21 1.35.1 1.87-.34l.06-.06a2 2 0 1 1 2.83 2.83l-.06.06c-.44.52-.55 1.27-.34 1.87.19.51.6 1 1.6 1.04H21a2 2 0 1 1 0 4h-.09c-1 .04-1.41.53-1.6 1.04Z"/></svg>',
  book: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><path d="M4 19.5V5.5A2.5 2.5 0 0 1 6.5 3H20v15H6.5A2.5 2.5 0 0 0 4 20.5v-1Z"/></svg>',
  users: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2" transform="translate(2)"/><circle cx="9" cy="7" r="4" transform="translate(2)"/><path d="M23 21v-2a4 4 0 0 0-3-3.87M16 3.13a4 4 0 0 1 0 7.75"/></svg>',
  chart: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><path d="M3 3v18h18" stroke-linecap="round"/><path d="M7 15l4-4 3 3 5-6" stroke-linecap="round" stroke-linejoin="round"/></svg>',
  search: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="11" cy="11" r="7"/><path d="M21 21l-4.3-4.3" stroke-linecap="round"/></svg>',
  chevron: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M6 9l6 6 6-6" stroke-linecap="round" stroke-linejoin="round"/></svg>',
  moon: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><path d="M21 12.8A9 9 0 1 1 11.2 3a7 7 0 0 0 9.8 9.8Z"/></svg>',
  menu: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M4 6h16M4 12h16M4 18h16" stroke-linecap="round"/></svg>',
};

const NAV_ITEMS = [
  { key:'dashboard', label:'Dashboard', href:'index.html', icon:'dashboard' },
  { key:'create',    label:'Create Course', href:'create-course.html', icon:'create' },
  { key:'my',        label:'My Courses', href:'my-courses.html', icon:'courses' },
  { key:'platform',  label:'Platform Courses', href:'courses.html', icon:'platform' },
];
const ADMIN_ITEMS = [
  { key:'admin-courses',  label:'Courses',  href:'courses.html', icon:'book' },
  { key:'admin-users',    label:'Users',    href:'admin-users.html', icon:'users' },
  { key:'admin-analytics',label:'Analytics',href:'admin-analytics.html', icon:'chart' },
];

function renderShell(active){
  const user = (typeof Auth !== 'undefined' && Auth.getUser()) || { fullName: 'Guest Learner' };
  const initials = (user.fullName || user.username || 'GL').split(' ').map(w=>w[0]).slice(0,2).join('').toUpperCase();

  const navHTML = NAV_ITEMS.map(it => `
    <a class="nav-item ${active===it.key?'is-active':''}" href="${it.href}">
      ${ICONS[it.icon]}<span>${it.label}</span>
    </a>`).join('');

  const adminHTML = ADMIN_ITEMS.map(it => `
    <a class="nav-item ${active===it.key?'is-active':''}" href="${it.href}">
      ${ICONS[it.icon]}<span>${it.label}</span>
    </a>`).join('');

  const sidebar = `
    <aside class="sidebar" id="sidebar">
      <div class="sidebar__brand"><span class="dot"></span> E-Learning</div>
      <nav class="sidebar__nav">
        ${navHTML}
        <div class="sidebar__label">Administration</div>
        <div class="sidebar__sub">${adminHTML}</div>
      </nav>
      <div class="sidebar__spacer"></div>
      <div class="sidebar__footer">
        <button class="theme-toggle" id="themeToggle" aria-pressed="true">
          ${ICONS.moon}<span class="knob"></span> Dark mode
        </button>
      </div>
    </aside>
    <div class="sidebar__scrim" id="scrim"></div>`;

  const topbar = `
    <header class="topbar">
      <button class="burger" id="burger" aria-label="Open menu">${ICONS.menu}</button>
      <nav class="topbar__menu">
        <a href="about.html#why">Why E-learning?</a>
        <a href="about.html#how">How it works</a>
        <a href="about.html#earn">Earn with platform</a>
        <a href="courses.html">Find course</a>
      </nav>
      <div class="topbar__spacer"></div>
      <div class="topbar__search">
        ${ICONS.search}
        <input type="search" placeholder="Search courses…" id="topSearch" aria-label="Search courses">
      </div>
      <div class="topbar__user" id="userMenu">
        <div class="avatar">${initials}</div>
        ${ICONS.chevron}
      </div>
    </header>`;

  const appEl = document.getElementById('app');
  appEl.insertAdjacentHTML('afterbegin', sidebar);
  const main = document.createElement('div');
  main.innerHTML = topbar;
  appEl.querySelector('.sidebar__scrim').after(main.firstElementChild);

  // mobile nav
  const sidebarEl = document.getElementById('sidebar');
  document.getElementById('burger').addEventListener('click', () => sidebarEl.classList.add('is-open'));
  document.getElementById('scrim').addEventListener('click', () => sidebarEl.classList.remove('is-open'));

  // search -> courses page
  const searchInput = document.getElementById('topSearch');
  searchInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter' && searchInput.value.trim()){
      window.location.href = `courses.html?q=${encodeURIComponent(searchInput.value.trim())}`;
    }
  });

  // user menu -> login or account
  document.getElementById('userMenu').addEventListener('click', () => {
    window.location.href = (typeof Auth !== 'undefined' && Auth.isLoggedIn()) ? 'my-courses.html' : 'login.html';
  });
}

document.addEventListener('DOMContentLoaded', () => {
  const page = document.body.dataset.active || '';
  renderShell(page);
});
