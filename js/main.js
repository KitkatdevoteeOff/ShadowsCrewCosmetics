function login() {
    const username = document.getElementById("username").value;
    const player = players.find(p => p.username === username);
    if(player) {
        currentPlayer = player;
        document.getElementById("login").style.display = "none";
        showSection("shop");
        updateShop();
        updateCoins();
    } else {
        alert("Pseudo introuvable !");
    }
}

function showSection(section) {
    ["shop", "coins", "promo"].forEach(sec => {
        document.getElementById(sec).style.display = sec === section ? "block" : "none";
    });
}

function updateShop() {
    const container = document.getElementById("cosmetics-list");
    container.innerHTML = "";
    cosmetics.forEach(c => {
        const owned = currentPlayer.cosmetics.includes(c.name) ? "✅ Possédé" : "";
        const btn = currentPlayer.coins >= c.price && !owned ? `<button onclick="buyCosmetic('${c.name}')">Acheter (${c.price} coins)</button>` : "";
        container.innerHTML += `<div>${c.name} - ${c.category} ${btn} ${owned}</div>`;
    });
}

function buyCosmetic(name) {
    const cosmetic = cosmetics.find(c => c.name === name);
    if(currentPlayer.coins >= cosmetic.price) {
        currentPlayer.coins -= cosmetic.price;
        currentPlayer.cosmetics.push(cosmetic.name);
        updateShop();
        updateCoins();
    } else {
        alert("Coins insuffisants !");
    }
}

function updateCoins() {
    document.getElementById("coins-count").innerText = "Coins : " + currentPlayer.coins;
    document.getElementById("hours-played").innerText = "Heures jouées : " + currentPlayer.hours;
}

function applyPromo() {
    const code = document.getElementById("promo-input").value;
    const promo = promoCodes.find(p => p.code === code);
    if(promo && !promo.usedBy.includes(currentPlayer.username)) {
        currentPlayer.coins += promo.coins;
        promo.usedBy.push(currentPlayer.username);
        document.getElementById("promo-result").innerText = "Code appliqué ! +" + promo.coins + " coins";
        updateCoins();
        updateShop();
    } else {
        document.getElementById("promo-result").innerText = "Code invalide ou déjà utilisé";
    }
}
