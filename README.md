## Webový repozitar pro vývojové artefakty - Tým A
### Vedení:
Mgr. Filip Nguyen, učo 208428 (vedoucí)

### Oficiální zadání:
Tento projekt má za cíl vytvořit implementaci webové aplikace, která umožní uchovávat vyvojářské artefakty a umožňuje je spravovat. Této oblasti se v současné době věnuje v Enterprise Middlewaru značná pozornost.

Webová aplikace bude mít webové rozhraní a umožní nahrávat následující artefakty:

* WAR archivy
* XSD schémata
* WSDL dokumenty

Nahrávat daný artefakt je možné vícekrát. Pokud se jedná o stejný artefakt, bude novým nahráním přidána nová verze tohoto artefaktu. Musí být uchovány všecky verze, které byly kdy nahrány a všechny musí být následně pro uživatele přístupné. Po nahrání souboru (artefaktu) bude daný aretfakt v systému uložen. Na persistenci všeho v této aplikaci použíjte nějakou XML databázi.

Po nahrání daného artefaktu z něj budou vyextrahovány metadata a opět uloženy v systému tak, aby bylo možné artefakt nahlížet společně s danými metadaty. Následující metadata budou extrahovány:

* Pro WAR archivy se vytáhne web.xml a bude k náhledu. Dále se vyextrahuje seznam listenerů a filtrů.
* Pro XSD schémata se vyextrahuje seznam typů (simple a complex) a seznam elementů a atributů.
* Pro WSDL dokumenty se vyextrahuje seznam operací spolu s informací o requestu a response zprávách.

Webové rozhraní nadále umožní:

* Nahlížet jednotlivé artefakty s jejich metadaty.
* Bude zde vidět seznam verzí artefaktu.
* Bude možné vyhledávat jednotlivé artefakty podle metadat.

*Imported from https://code.google.com/p/webovy-repozitar-pb138/, RIP Google Code*

## Instalace a spuštění

1. Stáhnout **zdrojový kód** - read-only checkout **aktuální verze**
2. Potřeba mít stažený server **Apache Tomcat**, ke stažení zde: http://tomcat.apache.org/download-70.cgi
3. **Clean and Build** Project
4. Spustit **InitializeDTB.java** pro inicializaci databáze (vytvoří se BaseXDB v user.home složce)
5. **Run Project**
