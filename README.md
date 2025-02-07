Description
Ce projet est une application e-commerce qui permet aux utilisateurs de parcourir des produits, d'ajouter des articles à leur panier et de gérer leurs achats. Il comprend une interface utilisateur développée avec Angular et un back-end basé sur Spring Boot.

Fonctionnalités
Front-end
Shop
Affichage des produits avec toutes leurs informations pertinentes
Ajout d'un produit au panier directement depuis la liste des produits
Suppression d'un produit du panier
Affichage du nombre d'articles dans le panier via un badge
Visualisation des produits du panier
Contact
Ajout d'un onglet "Contact" dans la barre latérale
Création d'une page de contact avec un formulaire
Champs requis :
Email (obligatoire)
Message (obligatoire, max 300 caractères)
Affichage d'un message de confirmation après envoi
Bonus
Ajout d'un système de pagination et/ou de filtrage sur la liste des produits
Modification de la quantité des produits directement depuis la liste et le panier
Back-end
Gestion des produits
Ressource	POST	GET	PATCH	PUT	DELETE
/products	✅	✅	❌	❌	❌
/products/:id	❌	✅	✅	❌	✅
Un produit est défini comme suit :
