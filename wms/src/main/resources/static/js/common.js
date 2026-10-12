/* ============================================================
   船员管理系统 · 公共脚本
   API 封装 / 导航骨架 / 弹窗 / 确认框 / Toast / 日期与徽章工具
   ============================================================ */

/* ---------- 选择器 ---------- */
const $ = (s, r = document) => r.querySelector(s);
const $$ = (s, r = document) => Array.from(r.querySelectorAll(s));

/* ---------- 图标集(内联 SVG, 无外部依赖) ---------- */
const ICONS = {
  home: '<path d="M3 10.5 12 3l9 7.5"/><path d="M5 10v10h14V10"/><path d="M9 20v-6h6v6"/>',
  crew: '<path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2"/><circle cx="9" cy="7" r="4"/><path d="M22 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.75"/>',
  cert: '<circle cx="12" cy="8" r="6"/><path d="M15.5 13 17 22l-5-3-5 3 1.5-9"/>',
  contract: '<path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/><path d="M14 2v6h6"/><path d="M16 13H8"/><path d="M16 17H8"/>',
  training: '<path d="M22 10 12 5 2 10l10 5 10-5z"/><path d="M6 12v5c0 1.66 2.69 3 6 3s6-1.34 6-3v-5"/><path d="M22 10v6"/>',
  ship: '<path d="M2 21c.6.5 1.2 1 2.5 1 2.5 0 2.5-2 5-2 1.3 0 1.9.5 2.5 1s1.2 1 2.5 1c2.5 0 2.5-2 5-2 1.3 0 1.9.5 2.5 1"/><path d="M19.4 20A11.9 11.9 0 0 0 21 14l-9-4-9 4c0 2.9.9 5.3 2.8 7.8"/><path d="M19 13V7a2 2 0 0 0-2-2H7a2 2 0 0 0-2 2v6"/><path d="M12 10v4"/><path d="M12 2v3"/>',
  report: '<path d="M3 3v18h18"/><path d="M8 17v-3"/><path d="M12 17v-6"/><path d="M16 17V7"/>',
  anchor: '<circle cx="12" cy="5" r="3"/><path d="M12 22V8"/><path d="M5 12H2a10 10 0 0 0 20 0h-3"/>',
  compass: '<circle cx="12" cy="12" r="10"/><path d="m16.2 7.8-2.1 6.3-6.3 2.1 2.1-6.3 6.3-2.1Z"/>',
  logout: '<path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><path d="m16 17 5-5-5-5"/><path d="M21 12H9"/>',
  search: '<circle cx="11" cy="11" r="7"/><path d="m21 21-4.3-4.3"/>',
  plus: '<path d="M12 5v14M5 12h14"/>',
  edit: '<path d="M17 3a2.85 2.85 0 1 1 4 4L7.5 20.5 2 22l1.5-5.5Z"/>',
  trash: '<path d="M3 6h18"/><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6"/><path d="M8 6V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/>',
  eye: '<path d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7-10-7-10-7Z"/><circle cx="12" cy="12" r="3"/>',
  close: '<path d="M18 6 6 18M6 6l12 12"/>',
  chevL: '<path d="m15 18-6-6 6-6"/>',
  chevR: '<path d="m9 18 6-6-6-6"/>',
  calendar: '<rect x="3" y="4" width="18" height="17" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/>',
  warn: '<path d="M10.3 3.9 1.8 18a2 2 0 0 0 1.7 3h17a2 2 0 0 0 1.7-3L13.7 3.9a2 2 0 0 0-3.4 0Z"/><path d="M12 9v4"/><path d="M12 17h.01"/>',
  check: '<path d="M20 6 9 17l-5-5"/>',
  phone: '<path d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6A19.79 19.79 0 0 1 2.12 4.18 2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72c.13.96.36 1.9.7 2.81a2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45c.91.34 1.85.57 2.81.7A2 2 0 0 1 22 16.92z"/>',
  idcard: '<rect x="2" y="5" width="20" height="14" rx="2"/><circle cx="8" cy="11" r="2"/><path d="M4.5 16c.5-2.2 2.2-3 3.5-3s3 .8 3.5 3"/><path d="M14 9h5"/><path d="M14 13h5"/>',
  briefcase: '<rect x="2" y="7" width="20" height="13" rx="2"/><path d="M16 7V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v2"/>'
};

function icon(name, size) {
  const s = size ? ` width="${size}" height="${size}"` : '';
  return `<svg class="ic"${s} viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">${ICONS[name] || ''}</svg>`;
}

/* ---------- 基础工具 ---------- */
function esc(s) {
  return String(s ?? '').replace(/[&<>"']/g, c => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' }[c]));
}

function getUser() {
  try { return JSON.parse(localStorage.getItem('user') || 'null'); } catch (e) { return null; }
}

function guard() {
  if (!getUser()) location.href = 'login.html';
}

function logout() {
  localStorage.removeItem('user');
  location.href = 'login.html';
}

/* ---------- API 封装: 成功返回 data, 失败抛错 ---------- */
async function api(path, opts = {}) {
  let res;
  try {
    res = await fetch(path, opts);
  } catch (e) {
    throw new Error('无法连接服务器,请确认后端已启动');
  }
  let json = null;
  try { json = await res.json(); } catch (e) { /* 非 JSON 响应 */ }
  if (json && json.code === 200) return json.data;
  throw new Error((json && json.msg) || ('服务器返回异常 (HTTP ' + res.status + ')'));
}

function post(path, body) {
  return api(path, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body)
  });
}
function put(path, body) {
  return api(path, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body)
  });
}
function del(path) { return api(path, { method: 'DELETE' }); }

/* ---------- Toast ---------- */
function toast(msg, type = 'ok') {
  const root = $('#toast-root');
  if (!root) { alert(msg); return; }
  const el = document.createElement('div');
  el.className = 'toast ' + type;
  const ic = type === 'err' ? 'warn' : (type === 'info' ? 'anchor' : 'check');
  el.innerHTML = icon(ic) + `<span>${esc(msg)}</span>`;
  root.appendChild(el);
  setTimeout(() => {
    el.classList.add('out');
    setTimeout(() => el.remove(), 260);
  }, 3000);
}

/* ---------- 弹窗 ---------- */
let _modals = [];

function showModal({ title = '', body = '', foot = '', width = 560, onOpen = null, closable = true }) {
  const root = document.createElement('div');
  root.className = 'modal-overlay';
  root.innerHTML = `
    <div class="modal" style="width:${width}px">
      <div class="modal-head">
        <h3>${esc(title)}</h3>
        ${closable ? `<button class="btn-icon" data-close>${icon('close')}</button>` : ''}
      </div>
      <div class="modal-body">${body}</div>
      ${foot ? `<div class="modal-foot">${foot}</div>` : ''}
    </div>`;
  $('#modal-root').appendChild(root);
  requestAnimationFrame(() => requestAnimationFrame(() => root.classList.add('show')));

  const close = () => {
    if (!root.isConnected) return;
    root.classList.remove('show');
    setTimeout(() => root.remove(), 190);
    _modals = _modals.filter(m => m !== root);
  };
  _modals.push(root);

  if (closable) {
    root.addEventListener('click', e => {
      if (e.target === root || e.target.closest('[data-close]')) close();
    });
  }
  setTimeout(() => {
    const first = root.querySelector('input, select, textarea, button.btn-primary');
    if (first) first.focus();
  }, 160);
  if (onOpen) onOpen(root);
  return { root, close };
}

document.addEventListener('keydown', e => {
  if (e.key !== 'Escape' || !_modals.length) return;
  // 关闭最上层弹窗(点击其关闭按钮,保证各页的 onOpen 回调也能正常解绑)
  const m = _modals[_modals.length - 1];
  const c = $(`[data-close]`, m);
  if (c) c.click();
  else { m.classList.remove('show'); setTimeout(() => m.remove(), 190); _modals.pop(); }
});

/* ---------- 自定义确认框 ---------- */
function ask(msg, { title = '确认操作', okText = '确定', danger = true } = {}) {
  return new Promise(resolve => {
    const m = showModal({
      title,
      width: 420,
      body: `<div class="ask-body ${danger ? 'danger' : ''}">${icon('warn')}<p>${esc(msg)}</p></div>`,
      foot: `
        <button class="btn btn-ghost" data-act="cancel">取消</button>
        <button class="btn ${danger ? 'btn-danger' : 'btn-primary'}" data-act="ok">${esc(okText)}</button>`,
      onOpen(root) {
        $('[data-act="cancel"]', root).onclick = () => { m.close(); resolve(false); };
        $('[data-act="ok"]', root).onclick = () => { m.close(); resolve(true); };
      }
    });
  });
}

/* ---------- 日期工具 ---------- */
function parseDate(s) {
  if (!s) return null;
  const p = String(s).slice(0, 10).split('-');
  if (p.length !== 3) return null;
  return new Date(+p[0], +p[1] - 1, +p[2]);
}
function fmtDate(s) { return s ? String(s).slice(0, 10) : '—'; }
function daysLeft(s) {
  const d = parseDate(s);
  if (!d) return null;
  const t = new Date();
  t.setHours(0, 0, 0, 0);
  return Math.round((d - t) / 86400000);
}
function daysChip(s) {
  const n = daysLeft(s);
  if (n === null) return '';
  if (n < 0) return `<span class="chip red">已过期 ${-n} 天</span>`;
  if (n === 0) return `<span class="chip amber">今天到期</span>`;
  if (n <= 30) return `<span class="chip amber">剩 ${n} 天</span>`;
  return `<span class="chip gray">${n} 天后</span>`;
}

/* ---------- 徽章 ---------- */
function chip(text, tone = 'gray', ic = '') {
  return `<span class="chip ${tone}">${ic ? icon(ic) : ''}${esc(text ?? '—')}</span>`;
}
const CREW_STATUS_TONE = { '在船': 'green', '在岸': 'blue', '休假': 'amber' };
function crewBadge(s) { return chip(s || '—', CREW_STATUS_TONE[s] || 'gray'); }
function contractBadge(s) {
  const t = { '在期': 'teal', '已到期': 'red', '已解除': 'gray' };
  return chip(s || '—', t[s] || 'gray');
}
function passBadge(f) {
  if (f === 1 || f === '1' || f === true) return chip('合格', 'green', 'check');
  if (f === 0 || f === '0') return chip('不合格', 'red');
  return chip('未录入', 'gray');
}

/* ---------- 头像 ---------- */
function avatarHTML(name, size = 34) {
  const nm = String(name || '?').trim();
  const ch = nm.slice(0, 1);
  let h = 0;
  for (const c of nm) h = (h * 31 + c.charCodeAt(0)) % 360;
  return `<span class="avatar" style="width:${size}px;height:${size}px;font-size:${Math.round(size * .42)}px;background:linear-gradient(135deg,hsl(${h},62%,52%),hsl(${(h + 30) % 360},58%,38%))">${esc(ch)}</span>`;
}

/* ---------- 船员数据缓存(供名称映射与下拉框) ---------- */
const _crewCache = {};
async function fetchCrew(status) {
  const k = status || 'all';
  if (_crewCache[k]) return _crewCache[k];
  const url = `/api/crew/page?pageNum=1&pageSize=1000${status ? `&status=${encodeURIComponent(status)}` : ''}`;
  const data = await api(url);
  _crewCache[k] = data.records || [];
  return _crewCache[k];
}
async function crewMap() {
  const list = await fetchCrew();
  const m = {};
  list.forEach(c => { m[c.id] = { name: c.name, crewNo: c.crewNo, position: c.position, status: c.status }; });
  return m;
}
async function fillCrewSelect(sel, { all = false, status = '', selected = '', placeholder = '选择船员' } = {}) {
  const list = await fetchCrew(status);
  const first = all
    ? `<option value="">全部船员</option>`
    : `<option value="">${esc(placeholder)}</option>`;
  sel.innerHTML = first + list.map(c =>
    `<option value="${c.id}">${esc(c.crewNo || '—')} · ${esc(c.name)} · ${esc(c.position || '')}</option>`
  ).join('');
  if (selected !== '') sel.value = String(selected);
}

/* ---------- 分页渲染 ---------- */
function renderPager(el, { current, pages, total, onChange }) {
  if (!pages || pages <= 1) {
    el.innerHTML = `<span class="pg-info">共 ${total ?? 0} 条</span>`;
    return;
  }
  const nums = [];
  const win = 5;
  let s = Math.max(1, current - 2), e = Math.min(pages, s + win - 1);
  s = Math.max(1, e - win + 1);
  if (s > 1) { nums.push(1); if (s > 2) nums.push('…'); }
  for (let i = s; i <= e; i++) nums.push(i);
  if (e < pages) { if (e < pages - 1) nums.push('…'); nums.push(pages); }

  el.innerHTML = `
    <span class="pg-info">共 ${total ?? 0} 条</span>
    <div class="pager">
      <button class="pg" data-p="${current - 1}" ${current <= 1 ? 'disabled' : ''}>${icon('chevL')}</button>
      ${nums.map(n => n === '…'
        ? `<span class="pg-dots">…</span>`
        : `<button class="pg ${n === current ? 'on' : ''}" data-p="${n}">${n}</button>`).join('')}
      <button class="pg" data-p="${current + 1}" ${current >= pages ? 'disabled' : ''}>${icon('chevR')}</button>
    </div>`;
  $$('button[data-p]', el).forEach(b => {
    b.onclick = () => {
      const p = +b.dataset.p;
      if (p >= 1 && p <= pages && p !== current) onChange(p);
    };
  });
}

/* ---------- 空表格行 ---------- */
function emptyRow(cols, text, sub = '') {
  return `<tr><td colspan="${cols}"><div class="empty"><div class="e-ic">⚓</div><div class="e-t">${esc(text)}</div>${sub ? `<div class="e-s">${esc(sub)}</div>` : ''}</div></td></tr>`;
}

/* ---------- 导航骨架 ---------- */
const NAV = [
  ['index.html', 'home', '总览'],
  ['crew.html', 'crew', '船员管理'],
  ['cert.html', 'cert', '证书管理'],
  ['contract.html', 'contract', '合同管理'],
  ['training.html', 'training', '培训管理'],
  ['dispatch.html', 'ship', '派员管理'],
  ['report.html', 'report', '报表']
];

function initPage(active, title, subtitle = '') {
  guard();
  const u = getUser() || {};
  $('#sidebar').innerHTML = `
    <div class="logo">
      <span class="mark">${icon('compass')}</span>
      <div><div class="name">船员管理系统</div><div class="sub">CREW &amp; VESSEL</div></div>
    </div>
    <nav class="nav">
      ${NAV.map(([href, key, label]) =>
        `<a href="${href}" class="${key === active ? 'active' : ''}">${icon(key)}<span>${label}</span></a>`).join('')}
    </nav>
    <div class="side-foot">${icon('anchor')}海员档案 · 证书 · 合同<br><span>MARINE CREW WMS v2.0</span></div>`;

  const now = new Date();
  const week = '日一二三四五六'[now.getDay()];
  $('#topbar').innerHTML = `
    <div class="tb-title">
      <h1>${esc(title)}</h1>
      ${subtitle ? `<span class="tb-sub">${esc(subtitle)}</span>` : ''}
    </div>
    <div class="tb-right">
      <span class="tb-date">${icon('calendar')}${now.getFullYear()}年${now.getMonth() + 1}月${now.getDate()}日 周${week}</span>
      <span class="tb-user">
        ${avatarHTML(u.realName || u.username || '管', 30)}
        <span class="tb-uname">${esc(u.realName || u.username || '管理员')}</span>
        ${chip(u.role || '管理员', 'teal')}
      </span>
      <button class="btn btn-ghost btn-sm" onclick="logout()">${icon('logout')}退出</button>
    </div>`;
  document.title = title + ' · 船员管理系统';
}

/* ---------- URL 查询参数 ---------- */
const qs = new URLSearchParams(location.search);
function qCrewId() { return qs.get('crewId'); }
function qName() { return qs.get('name'); }
