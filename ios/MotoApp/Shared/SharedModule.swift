// MARK: - Shared Module
// Estructura compartida entre todas las features de iOS
// Conecta funciones entre módulos manteniendo la dependencia de cada uno
// Ejemplo: nube.enviaPermisos, autenticacion.signIn, etc.

import Foundation

@Observable
class MotoSharedState {
    var isAuthenticated: Bool = false
    var userProfile: UserProfile? = nil
    var cloudConnection: CloudConnectionState = .disconnected
}

enum CloudConnectionState {
    case disconnected, connecting, connected, error(String?)
}

struct UserProfile {
    let id: String
    let name: String
    let email: String
    let photoURL: URL?
}
