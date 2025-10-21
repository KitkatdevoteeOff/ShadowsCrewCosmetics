// comptes simulés
const players = [
    { username: "Player1", coins: 50, hours: 3, cosmetics: ["Cape Dragon"] },
    { username: "Player2", coins: 120, hours: 10, cosmetics: [] },
];

// cosmétiques disponibles
let cosmetics = [
    { name: "Cape Dragon", price: 100, category: "Cape" },
    { name: "Chapeau Cool", price: 50, category: "Chapeau" },
    { name: "Effet Flamme", price: 150, category: "Effet" }
];

// codes promo
let promoCodes = [
    { code: "WELCOME50", coins: 50, usedBy: [] },
    { code: "DRAGON10", coins: 10, usedBy: [] }
];

// joueur actuellement connecté (simulation)
let currentPlayer = null;
