const switchComponent = document.getElementById('switch-component');
const html = document.documentElement;

const toggleDarkMode = () => {
    html.classList.toggle('dark');
    localStorage.setItem('darkMode', html.classList.contains('dark') ? 'enabled' : 'disabled');

    // Mettre à jour l'état du switch
    switchComponent.checked = html.classList.contains('dark');
};

// Initialisation au chargement de la page
if (localStorage.getItem('darkMode') === 'enabled') {
    html.classList.add('dark');
    switchComponent.checked = true;
} else {
    html.classList.remove('dark');
    switchComponent.checked = false; // Important : garantir que le switch est synchro
}

switchComponent.addEventListener('change', toggleDarkMode);