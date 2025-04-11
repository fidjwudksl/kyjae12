function requestCode(mode) {
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

function verifyCode(mode) {
  const discordId = document.getElementById('discordId').value;
  const code = document.getElementById('code').value;

  fetch('/api/auth/discord/verify', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({discordId, code})
  }).then(res => {
    if (res.ok) {
      if (mode === 'find-id') {
        fetch('/api/recovery/find-id?discordId=' + discordId)
          .then(res => res.text())
          .then(text => document.getElementById('result').innerText = '아이디: ' + text);
      } else if (mode === 'reset-password') {
        document.getElementById('passwordSection').style.display = 'block';
      }
    } else {
      alert('인증 코드가 유효하지 않습니다.');
    }
  });
}

function resetPassword() {
  const discordId = document.getElementById('discordId').value;
  const newPassword = document.getElementById('newPassword').value;

  fetch('/api/recovery/reset-password', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({discordId, newPassword})
  }).then(res => res.text()).then(msg => {
    document.getElementById('result').innerText = msg;
  });
}