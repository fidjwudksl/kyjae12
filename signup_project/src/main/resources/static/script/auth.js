function requestCode() {
  const discordId = document.getElementById('discordId').value;
  fetch('/api/auth/discord/request', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({discordId})
  }).then(res => res.text()).then(msg => {
    alert(msg);
    document.getElementById('verifySection').style.display = 'block';
  });
}

function verifyAndSignup() {
  const discordId = document.getElementById('discordId').value;
  const code = document.getElementById('code').value;

  fetch('/api/auth/discord/verify', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({discordId, code})
  }).then(res => {
    if (res.ok) {
      const username = document.getElementById('username').value;
      const password = document.getElementById('password').value;
      const nickname = document.getElementById('nickname').value;
      fetch('/api/signup', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({username, password, nickname, discordId})
      }).then(res => res.text()).then(msg => {
        alert(msg);
        window.location.href = '/index.html';
      });
    } else {
      alert("인증 실패. 인증 코드를 확인해주세요.");
    }
  });
}

let usernameOk = false;
let nicknameOk = false;

function checkUsername() {
  const username = document.getElementById('username').value;
  if (!username) return;
  fetch('/api/check/username?username=' + username)
    .then(res => res.json())
    .then(exists => {
      const el = document.getElementById('usernameCheck');
      if (exists) {
        el.innerText = '이미 사용 중인 아이디입니다.';
        el.className = 'form-text text-danger';
        usernameOk = false;
      } else {
        el.innerText = '사용 가능한 아이디입니다.';
        el.className = 'form-text text-success';
        usernameOk = true;
      }
      updateRequestCodeButton();
    });
}

function checkNickname() {
  const nickname = document.getElementById('nickname').value;
  if (!nickname) return;
  fetch('/api/check/nickname?nickname=' + nickname)
    .then(res => res.json())
    .then(exists => {
      const el = document.getElementById('nicknameCheck');
      if (exists) {
        el.innerText = '이미 사용 중인 닉네임입니다.';
        el.className = 'form-text text-danger';
        nicknameOk = false;
      } else {
        el.innerText = '사용 가능한 닉네임입니다.';
        el.className = 'form-text text-success';
        nicknameOk = true;
      }
      updateRequestCodeButton();
    });
}

function updateRequestCodeButton() {
  document.getElementById('requestCodeBtn').disabled = !(usernameOk && nicknameOk);
}